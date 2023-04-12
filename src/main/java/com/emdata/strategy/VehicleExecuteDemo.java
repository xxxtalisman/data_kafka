package com.emdata.strategy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.emdata.common.enums.CityCodeEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.emdata.common.config.SpringContextHolder;
import com.emdata.convert.ObjectConverter;
import com.emdata.mapper.ivvs.IvvsSbbhZhMapper;
import com.emdata.mapper.ivvs.IvvsSourceDataMapper;
import com.emdata.mapper.ivvs.IvvsViolateCodeMapper;
import com.emdata.model.dao.IvvsSourceData;
import com.emdata.model.dao.IvvsViolateCode;
import com.emdata.model.vo.IvvsSbbhZhDO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hikvision.artemis.sdk.kafka.data.push.VehicleExecute;
import com.hikvision.artemis.sdk.kafka.data.result.ExecuteResult;
import com.hikvision.artemis.sdk.kafka.entity.parse.result.VehicleAlarmData;
import com.hikvision.artemis.sdk.kafka.entity.parse.result.VehicleListData;
import com.hikvision.artemis.sdk.kafka.entity.parse.result.VehiclePassData;

/**
 * 车辆数据业务处理实现Demo
 */
public class VehicleExecuteDemo extends VehicleExecute {

    Logger logger = LoggerFactory.getLogger(VehicleExecuteDemo.class);

    private static ObjectMapper mapper = new ObjectMapper();
    
    private IvvsSourceDataMapper ivvsSourceDataMapper = SpringContextHolder.getBean(IvvsSourceDataMapper.class);
    //private RedisTemplate redisTemplate = SpringContextHolder.getBean(RedisTemplate.class);
    
    private IvvsViolateCodeMapper ivvsViolateCodeMapper;
    
    private IvvsSbbhZhMapper ivvsSbbhZhMapper;
    
    public VehicleExecuteDemo(IvvsViolateCodeMapper ivvsViolateCodeMapper,IvvsSbbhZhMapper ivvsSbbhZhMapper) {
    	this.ivvsViolateCodeMapper = ivvsViolateCodeMapper;
    	this.ivvsSbbhZhMapper = ivvsSbbhZhMapper;
    }
    @Override
    public ExecuteResult execute(VehicleListData data) {
        try {
            //logger.info("这就是需要处理逻辑的地方，接收到的数据为{}",mapper.writeValueAsString(data));
            //这里处理业务转化参数为我们需要得参数
            if (!Objects.isNull(data)) {
            	logger.info("从kafka上接收到数据，开始处理违法数据，违法数据参数为：wfdata={}",mapper.writeValueAsString(data));            	
            	logger.info("");
            	List<VehicleAlarmData> vehicleAlarmNoRecogList = new ArrayList<VehicleAlarmData>();
            	if (CollectionUtils.isNotEmpty(data.getVehicleAlarmNoRecogList())) {
            		vehicleAlarmNoRecogList.addAll(data.getVehicleAlarmNoRecogList());
				}
            	List<VehiclePassData> plist = data.getVehiclePassRecogList();
            	List<VehiclePassData> pnolist = data.getVehiclePassNoRecogList();
            	if (CollectionUtils.isNotEmpty(plist)) {
            		logger.info("本次接收到二次过车数据-data:{}",mapper.writeValueAsString(plist));
            		for (VehiclePassData  d:plist) {
            			if ("yes".equals(d.getUphone())) {
            				VehicleAlarmData  da = new VehicleAlarmData();
                			BeanUtils.copyProperties(d, da);
                			da.setAlarmType("1223");
                			vehicleAlarmNoRecogList.add(da);
						}
            			if ("no".equals(d.getPilotSafebelt())||"no".equals(d.getVicePilotSafebelt())) {
            				VehicleAlarmData  da = new VehicleAlarmData();
                			BeanUtils.copyProperties(d, da);
                			da.setAlarmType("1240");
                			vehicleAlarmNoRecogList.add(da);
						}
            			
					}
				}
            	if (!CollectionUtils.isEmpty(vehicleAlarmNoRecogList)) {
            		logger.info("开始数据转换。。。");
            		List<IvvsSourceData> sourceDataList = new ArrayList<IvvsSourceData>();
            		for (VehicleAlarmData vehicleAlarmData : vehicleAlarmNoRecogList) {
            			logger.info("开始检查违法行为是否需要入库。。。");
                		Map<String,Object> columnMap = new HashedMap<String,Object>();
                		columnMap.put("code", vehicleAlarmData.getAlarmType());
                		List<IvvsViolateCode> vlist = ivvsViolateCodeMapper.selectByMap(columnMap);
                		if (CollectionUtils.isEmpty(vlist)) {
							logger.info("违法行为:{}未配置",vehicleAlarmData.getAlarmType());
							continue;
						}
                		IvvsViolateCode vcode = vlist.get(0);
                		if (vcode.getReceiveStatus() == null || vcode.getReceiveStatus() !=0) {
                			logger.info("违法行为:{}接收开关未打开",vehicleAlarmData.getAlarmType());
                			continue;
						}
            			IvvsSourceData sourceData = new IvvsSourceData();
                		//TODO 原始数据转换可入库数据关系
            			ObjectConverter.VehicleAlarmData2IvvsSourceData(vehicleAlarmData, sourceData);
            			//加判断设备编号和集指映射关联设备编号
            			QueryWrapper queryWrapper = new QueryWrapper();
            			queryWrapper.eq("cross_no", sourceData.getSbbh());
            			List<IvvsSbbhZhDO> ll = ivvsSbbhZhMapper.selectList(queryWrapper);
            			if (CollectionUtils.isNotEmpty(ll)) {
							for (IvvsSbbhZhDO sbzh:ll) {
								boolean flag = vehicleAlarmData.getDirectionIndex().equals(sbzh.getDirectionCode()) ||vehicleAlarmData.getDirectionName().equals(sbzh.getDirectionDesc());
								if (flag) {
									sourceData.setSbbh(sbzh.getJzSbbh());
									break;
								}
							}
						}
						//设置城市代码
            			sourceData.setCityCode(CityCodeEnum.City_410825.getCode());
            			if (sourceData.getZpsl()!=null&&sourceData.getZpsl()>0) {
            				sourceData.setCreatetime(new Date());
            				sourceDataList.add(sourceData);
						}
					}
            		
            		try {
            			logger.info("{} size 即将入库，入库数据为{}",sourceDataList.size(),mapper.writeValueAsString(sourceDataList));
            			if (CollectionUtils.isNotEmpty(sourceDataList)) {
            				logger.info("数据转换完毕，本次入库数据量应为:num={}",sourceDataList.size());
            				int insertnum = ivvsSourceDataMapper.batchInsert(sourceDataList);
                			logger.info("本次执行ivvsSourceDataMapper.batchInsert 影响行数为 num={}",insertnum);
						}else {
							logger.info("本次执行结束，未有需要入库的数据");
						}            			
					} catch (Exception e) {
						e.printStackTrace();
						logger.info("数据库插入异常 {}",e);
					}
            		
				} else {
					logger.info("违法参数为空，本次数据不处理");
				}
			}else {				
				logger.info("接收参数为空 data = {}",data);
			}
            return ExecuteResult.success();
        } catch (Exception e) { 
        	e.printStackTrace();
            logger.info("处理数据异常 {}",e);
            return ExecuteResult.retry();
        }

    }
}
