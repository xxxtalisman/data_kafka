package com.emdata.convert;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.emdata.common.config.SpringContextHolder;
import com.emdata.common.config.SysParamProperties;
import com.emdata.common.enums.HkDictEnum;
import com.emdata.common.util.DateTimeUtils;
import com.emdata.common.util.MyX509TrustManager;
import com.emdata.model.dao.IvvsSourceData;
import com.hikvision.artemis.sdk.kafka.entity.parse.result.VehicleAlarmData;

import cn.hutool.core.date.DateUtil;

public class ObjectConverter {
	
	private static String localDir;
	
	static {
		localDir = SpringContextHolder.getBean(SysParamProperties.class).getDownloadDirPath();
	}
	
	/**
	 * 转换kafka数据为sourcedata数据
	 * @param source
	 * @param dest
	 */
	public static void  VehicleAlarmData2IvvsSourceData(VehicleAlarmData source,IvvsSourceData dest) {
		//设备编号
		dest.setSbbh(source.getCrossingIndexCode());
		//dest.setClfl(source)
		//dest.setHpzl(WxPlateEnum.getGbCode(source.getPlateType()));
		//号牌种类 && 转换对应海康号牌种类映射
		String hpzl = HkDictEnum.hkPlateTypeToStandard(source.getPlateType(), source.getPlateNo(), source.getPlateColorName(), source.getVehicleType());
		dest.setHpzl(hpzl);
		//号牌号码
		dest.setHphm(source.getPlateNo());
		//车道号
		//dest.setWfdd(source.getLaneNo());
		try {
			//DateTimeUtils.dealDateFormat(source.getCaptureTime());
			//dest.setWfsj(DateUtils.parseDate(source.getCaptureTime(), "yyyy-MM-dd HH:mm:ss"));
			//违法时间定义转换
			dest.setWfsj(DateUtil.parse(DateTimeUtils.dealDateFormat(source.getCaptureTime()), "yyyy-MM-dd HH:mm:ss"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		dest.setCdh(source.getLaneNo());
		//违法类型
		dest.setWfxw(source.getAlarmType());
		//TODO 0729 新增获取方向代码
		dest.setFxdm(source.getDirectionIndex());
		dest.setXsfx(source.getDecorationName());
		//图片获取
		String url_1=remoteUrlpic2localpicurl(source.getImagePath1(),localDir);
		String url_2=remoteUrlpic2localpicurl(source.getImagePath2(),localDir);
		String url_3=remoteUrlpic2localpicurl(source.getImagePath3(),localDir);
		String url_4=remoteUrlpic2localpicurl(source.getImagePath4(),localDir);
		String url_5=remoteUrlpic2localpicurl(source.getImagePath5(),localDir);
		String url_6=remoteUrlpic2localpicurl(source.getImagePath6(),localDir);
		List<String> urlList = new ArrayList<String>();
		if (url_1 != null) {
			urlList.add(url_1);
		}
		if (url_2 != null) {
			urlList.add(url_2);
		}
		if (url_3 != null) {
			urlList.add(url_3);
		}
		if (url_4 != null) {
			urlList.add(url_4);
		}
		if (url_5 != null) {
			urlList.add(url_5);
		}
		if (url_6 != null) {
			urlList.add(url_6);
		}
		for (int i=1;i<=urlList.size();i++) {
			try {
				IvvsSourceData.class.getMethod("setZpstr"+i, String.class).invoke(dest, urlList.get(i-1));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		int zpslnum = urlList.size();
		dest.setZpsl(zpslnum);
		if (zpslnum == 0) {
			dest.setZpstr1(remoteUrlpic2localpicurl(source.getTargetPicUrl(),localDir));
			if (!StringUtils.isEmpty(dest.getZpstr1())) {
				dest.setZpsl(1);
			}
		}
//		设置算法默认值
		dest.setAlgStatus(-1);
		dest.setAlgTimes(0);
		dest.setAlgUuid(Long.parseLong("0"));
		dest.setAlgErrCode(0);
		dest.setSendThirdParty(0);
		dest.setSendTimes(0);
	}
	
	/**
	 * 将远程的图片下载到本地并转为地址
	 * @param remotePicUrl
	 * @param localDir
	 * @return
	 */
	public static String remoteUrlpic2localpicurl(String remotePicUrl,String localDir) {
		/*
		 * if (!StringUtils.isEmpty(remotePicUrl)) { return
		 * DownloadFileUtil.downLoad(remotePicUrl,localDir, IdUtil.simpleUUID(),null); }
		 * return null;
		 */
		String nowDateStr = DateUtil.format(new Date(), "yyyyMMdd");
		String dirpath = localDir+"/"+nowDateStr;
		java.io.File file = new java.io.File(dirpath);
		
		if (!file.exists()) {
			file.mkdirs();
		}
		String path = MyX509TrustManager.downLoadHttpsImage(localDir+"/"+nowDateStr, remotePicUrl);
		if (path != null) {
			return path.replaceAll("/data/public","");
		}
		return null;
	}
	
	public static void main(String...args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		IvvsSourceData dd = new IvvsSourceData();
		IvvsSourceData.class.getMethod("setZpstr"+1, String.class).invoke(dd, "1111");
		System.out.println(dd.toString());
	}
	
}
