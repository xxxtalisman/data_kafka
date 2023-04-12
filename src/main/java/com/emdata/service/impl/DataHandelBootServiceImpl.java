package com.emdata.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.emdata.common.enums.DataProcErrEnum;
import com.emdata.common.enums.DataProcTypeEnum;
import com.emdata.common.enums.LocalRemoteEnum;
import com.emdata.common.util.*;
import com.emdata.common.util.sf.SnowFlaskInstance;
import com.emdata.mapper.ivvs.IvvsSourceDataMapper;
import com.emdata.model.dao.DataProcErrLog;
import com.emdata.model.dao.IvvsSourceData;
import com.emdata.model.dao.IvvsSourceDataMore;
import com.emdata.model.dto.RecordDataDTO;
import com.emdata.service.DataProcErrLogService;
import com.emdata.service.IvvsRuleService;
import com.emdata.service.IvvsSourceDataMoreService;
import com.emdata.strategy.DataTargetClient;
import com.emdata.strategy.IDataTarget;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.*;

/**
 * @Author: txw
 * @Date: 2019/12/26 10:45
 */

@Slf4j
@Service
public class DataHandelBootServiceImpl {


    @Autowired
    private IvvsRuleService ruleService;


    @Resource
    private DataTargetClient<String> client;


    @Resource
    private DataProcErrLogService dataProcErrLogService;

    @Resource
    private IvvsSourceDataMapper ivvsSourceDataMapper;

    @Resource
    private IvvsSourceDataMoreService ivvsSourceDataMoreService;

    @Value("${download.dir.path}")
    private String downloadDirPath;


    /**
     * 获取kafka数据
     * @param recordData
     */
    public void handleData(RecordDataDTO recordData){
        //定义原始图片下载保存地址downloadDirPath: /data/public/violation/
        String path = downloadDirPath  + DateUtil.formatDate(new DateTime())+"/";
        //城市代码生产处理不同业务逻辑
        String cityCode  = BusinessHelper.getCityCode();
        IDataTarget target = getTargetObj(cityCode);
        RespRet respRet = null;
        try {
            respRet = target.action(recordData , cityCode);
            if(ObjectUtil.isNotNull(respRet.getData())){
                Map<String,Object> objectMap = (Map<String, Object>) respRet.getData();
                if(objectMap.isEmpty()){
                    respRet = RespRet.failed(StrUtil.format("{} objectMap isEmpty {}", cityCode,respRet));
                }else {
                    //解析数据实体
                    IvvsSourceData sourceData = null;
                    IvvsSourceDataMore sourceDataMore = null;
                    Iterator keys = objectMap.keySet().iterator();
                    while (keys.hasNext()) {
                        String key = (String) keys.next();
                        switch (key){
                            case Constant.SOURCE_DATA:
                                sourceData = (IvvsSourceData) objectMap.get(Constant.SOURCE_DATA);
                                break;
                            case Constant.SOURCE_DATA_MORE:
                                sourceDataMore = (IvvsSourceDataMore) objectMap.get(Constant.SOURCE_DATA_MORE);
                                break;
                            default:
                                break;
                        }
                    }
                    if(null != sourceData){
                        //处理数据和检测入库封装数据
                        sourceData = packageData(sourceData, path, cityCode);
                        if(null != sourceData){
                            List<IvvsSourceData> ivvsSourceDataList = new ArrayList<>();
                            sourceData.setUuid(SnowFlaskInstance.getInstance().GetKeyGenerator().generateKey().longValue());
                            ivvsSourceDataList.add(sourceData);
                            if(ivvsSourceDataMapper.batchInsert(ivvsSourceDataList) > 0){
                                log.info("处理数据成功{} {} {} {}",sourceData.getHphm(),sourceData.getHpzl(),sourceData.getSbbh(),sourceData.getWfsj());
                                if(sourceDataMore!=null) {
                                    sourceDataMore.setUuid(sourceData.getUuid());
                                    ivvsSourceDataMoreService.save(sourceDataMore);
                                }
                            }else{
                                log.info("重复数据不插入数据库{} {} {} {}",sourceData.getHphm(),sourceData.getHpzl(),sourceData.getSbbh(),sourceData.getWfsj());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage() , e);
            respRet = RespRet.failed(ThrowableUtil.getStackTrace(e));
        }
        log.info("handleData action city {} respRet {}",cityCode,respRet);
    }


    private IvvsSourceData packageData(IvvsSourceData dataEntity, String path,String cityCode){
        log.info("开始组装数据hphm={},wfxw={},wfsj={}", dataEntity.getHphm(),dataEntity.getWfxw(),dataEntity.getWfsj());
        try {
            /**
             * 检测设备接入开关和违法行为接入开关
             */
           /* if (!checkRule(dataEntity)) {
                return null;
            }*/
            if (StrUtil.isBlank(checkParams(dataEntity))) {
                return null;
            }
            dataEntity.setCityCode(cityCode);
            dataEntity.setIlocalremote(LocalRemoteEnum.LOCAL.getCode());
            dataEntity.setCreatetime(new Date());
            if (StrUtil.isBlank(procImg(dataEntity,path,dataEntity))) {
                return null;
            }
            //设置算法状态默认值
            dataEntity.setAlgStatus(-1);
        } catch (BeansException e) {
            log.error("组装数据失败{}",e);
            saveDataProcErrLog(dataEntity,DataProcErrEnum.BEANS_EXCEPTION,e.toString());
            return null;
        }
        log.info("组装数据结束hphm={},wfxw={},wfsj={}",dataEntity.getHphm(),dataEntity.getWfxw(),dataEntity.getWfsj());
        return dataEntity;
    }


    /**
     * 检测参数
     * @param dataEntity
     * @return
     */
    private String checkParams(IvvsSourceData dataEntity) {
        if (StrUtil.isEmpty(dataEntity.getSbbh())) {
            log.error("设备编号为空，不能保存数据hphm={},wfsj={}",dataEntity.getHphm(),dataEntity.getWfsj());
            saveDataProcErrLog(dataEntity,DataProcErrEnum.SBBH_EMPTY,null);
            return null;
        }
        if (StrUtil.isEmpty(dataEntity.getHphm())) {
            log.error("号牌号码为空，不能保存数据hphm={},wfsj={}",dataEntity.getHphm(),dataEntity.getWfsj());
            saveDataProcErrLog(dataEntity,DataProcErrEnum.HPHM_EMPTY,null);
            return null;
        }
        if (StrUtil.isEmpty(dataEntity.getZpstr1())) {
            log.error("第一张图片为空，不能保存数据hphm={},wfsj={}",dataEntity.getHphm(),dataEntity.getWfsj());
            saveDataProcErrLog(dataEntity,DataProcErrEnum.FIRST_IMG_EMPTY,null);
            return null;
        }
        if (ObjectUtil.isEmpty(dataEntity.getWfsj())) {
            log.error("违法时间为空，不能保存数据hphm={},wfsj={}",dataEntity.getHphm(),dataEntity.getWfsj());
            saveDataProcErrLog(dataEntity,DataProcErrEnum.WFSJ_EMPTY,null);
            return null;
        }
        if (StrUtil.isEmpty(dataEntity.getWfxw())) {
            log.error("违法行为为空，不能保存数据hphm={},wfsj={}",dataEntity.getHphm(),dataEntity.getWfsj());
            saveDataProcErrLog(dataEntity,DataProcErrEnum.WFXW_EMPTY,null);
            return null;
        }
        return "OK";
    }


    private ExecutorService imgPool = new ThreadPoolExecutor(0, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());


    /**
     * 下载图片组装路径地址
     * @param dataEntity
     * @param path
     * @param serInputdata
     * @return
     */
    private String procImg(IvvsSourceData dataEntity,String path,IvvsSourceData serInputdata) {
        boolean existZpstr2 = StrUtil.isNotBlank(dataEntity.getZpstr2());
        boolean existZpstr3 = StrUtil.isNotBlank(dataEntity.getZpstr3());
        boolean existZpstr4 = StrUtil.isNotBlank(dataEntity.getZpstr4());
        boolean existWfspdz = StrUtil.isNotBlank(dataEntity.getWfspdz());
        int zpsl = getZpsl(dataEntity);
        CountDownLatch countDownLatch = new CountDownLatch(existWfspdz ? zpsl+1 : zpsl);
        imgPool.execute(()->packageImg(dataEntity.getZpstr1(),null,path,serInputdata,"setZpstr1",String.class,countDownLatch));
        if (existZpstr2) {
            imgPool.execute(()->packageImg(dataEntity.getZpstr2(),null,path,serInputdata,"setZpstr2",String.class,countDownLatch));
        }
        if (existZpstr3) {
            imgPool.execute(()->packageImg(dataEntity.getZpstr3(),null,path,serInputdata,"setZpstr3",String.class,countDownLatch));
        }
        if (existZpstr4) {
            imgPool.execute(()->packageImg(dataEntity.getZpstr4(),null,path,serInputdata,"setZpstr4",String.class,countDownLatch));
        }
        if (existWfspdz) {
            imgPool.execute(()->packageImg(dataEntity.getWfspdz(),null,path,serInputdata,"setWfspdz",String.class,countDownLatch));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error("countDownLatch中断",e);
        }
        if (StrUtil.isBlank(serInputdata.getZpstr1())) {
            log.error("第一张图片保存失败，不能保存数据imgpath={}",dataEntity.getZpstr1());
            saveDataProcErrLog(dataEntity,DataProcErrEnum.SAVE_FIRST_IMG_FAIL,dataEntity.getZpstr1());
            return null;
        }
        if (existZpstr2 && StrUtil.isBlank(serInputdata.getZpstr2())) {
            log.error("第二张图片保存失败，不能保存数据imgpath={}",dataEntity.getZpstr2());
            saveDataProcErrLog(dataEntity,DataProcErrEnum.SAVE_SECOND_IMG_FAIL,dataEntity.getZpstr2());
            return null;
        }
        if (existZpstr3 && StrUtil.isBlank(serInputdata.getZpstr3())) {
            log.error("第三张图片保存失败，不能保存数据imgpath={}",dataEntity.getZpstr3());
            saveDataProcErrLog(dataEntity,DataProcErrEnum.SAVE_THIRD_IMG_FAIL,dataEntity.getZpstr3());
            return null;
        }
        if (existZpstr4 && StrUtil.isBlank(serInputdata.getZpstr4())) {
            log.error("第四张图片保存失败，不能保存数据imgpath={}",dataEntity.getZpstr4());
            saveDataProcErrLog(dataEntity,DataProcErrEnum.SAVE_FOURTH_IMG_FAIL,dataEntity.getZpstr4());
            return null;
        }
        serInputdata.setZpsl(zpsl);
        return "OK";
    }


    private void packageImg(String zpstr,byte[] photo,String path,IvvsSourceData serInputdata,String methodName,Class parameterType,CountDownLatch countDownLatch){
        try {
            String impPath = DownloadFileUtil.downLoad(zpstr,path, IdUtil.simpleUUID(),photo);
            Class clazz = serInputdata.getClass();
            Method method = clazz.getDeclaredMethod(methodName,parameterType);
            method.invoke(serInputdata,impPath);
        } catch (NoSuchMethodException e) {
            log.error("反射方法失败",e);
        } catch (IllegalAccessException e) {
            log.error("方法无权访问",e);
        } catch (InvocationTargetException e) {
            log.error("反射调用内部方法失败",e);
        }finally {
            countDownLatch.countDown();
        }
    }


    /**
     * 最大图片张数
     */
    private final int MAX_IMG_NUM = 4;

    private Integer getZpsl(IvvsSourceData dataEntity) {
        int zpsl = 0;
        try {
            Class clazz = dataEntity.getClass();
            for (int i = 1; i <= MAX_IMG_NUM; i++) {
                Method method = clazz.getDeclaredMethod("getZpstr"+i,null);
                String zpstr = (String)method.invoke(dataEntity,null);
                if (StrUtil.isNotBlank(zpstr)) {
                    zpsl++;
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return zpsl;
    }


    /**
     * 获取各城市具体实现对象
     * @return
     */
    private IDataTarget getTargetObj(String cityCode) {
        IDataTarget target = client.getSendTarget(Constant.DATA_TARGET_PREFIX + cityCode);
        return target;
    }


    /**
     * 检测设备和违法行为
     * @param entity
     * @return
     */
    private boolean checkRule(IvvsSourceData entity) {
        List<String> sbbhinfoList = ruleService.getDataInsertDeviceRule();
        if (!CollectionUtil.contains(sbbhinfoList, entity.getSbbh())) {
            saveDataProcErrLog(entity,DataProcErrEnum.SBBH_LIMIT_INSERT,"sbbh="+entity.getSbbh());
            log.info("设备{}限制接入数据,请配置设备或开启设备接入开关",entity.getSbbh());
            return false;
        }
        List<String> violateCodes = ruleService.getDataInsertIllegalActRule();
        if (!CollectionUtil.contains(violateCodes, entity.getWfxw())) {
            saveDataProcErrLog(entity, DataProcErrEnum.WFXW_LIMIT_INSERT,"wfxw="+entity.getWfxw());
            log.info("违法行为{}限制接入数据,请配置违法行为或开启违法行为接入开关",entity.getWfxw());
            return false;
        }
        return true;
    }


    /**
     * 错误记录保存入库
     * @param dataEntity
     * @param errEnum
     * @param addMsg
     */
    private void saveDataProcErrLog(IvvsSourceData dataEntity, DataProcErrEnum errEnum,String addMsg){
        dataProcErrLogService.save(getDataProcErrLog(dataEntity,errEnum,addMsg));
    }


    /**
     * 组装错误log数据记录
     * @param dataEntity
     * @param errEnum
     * @param addMsg
     * @return
     */
    private DataProcErrLog getDataProcErrLog(IvvsSourceData dataEntity, DataProcErrEnum errEnum, String addMsg){
        DataProcErrLog dataProcErrLog = new DataProcErrLog();
        dataProcErrLog.setCreateTime(new Date());
        dataProcErrLog.setErrCode(errEnum.getErrCode());
        dataProcErrLog.setErrMsg(errEnum.getErrMsg()+":"+addMsg);
        dataProcErrLog.setHphm(dataEntity.getHphm());
        dataProcErrLog.setType(DataProcTypeEnum.INPUT.getCode());
        dataProcErrLog.setWfdd(dataEntity.getWfdd());
        if (ObjectUtil.isNotNull(dataEntity.getWfsj())) {
            dataProcErrLog.setWfsj(dataEntity.getWfsj());
        }
        dataProcErrLog.setWfxw(dataEntity.getWfxw());
        return dataProcErrLog;
    }

}
