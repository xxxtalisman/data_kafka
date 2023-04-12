package com.emdata.common.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.emdata.common.exception.ServiceException;
import com.emdata.common.enums.PropertyKeyEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class BusinessHelper {

    /***
     * 与城市绑定属性
     */
    private static final Map<String, Map<String, String>> CITY_PROPS_MAP = new ConcurrentHashMap<>();

    /***
     * 基本属性
     */
    private static final Map<String, String> BASE_PROPS_MAP = new ConcurrentHashMap();

    public static Map<String, String> getCityProperty(String cityCode) {
        Map<String, String> map = CITY_PROPS_MAP.get(cityCode);
        if (CollUtil.isEmpty(map)) {
            throw new ServiceException(StrUtil.format("该城市({})未配置属性,请检查ivvs_properties表" , cityCode));
        }
        return map;
    }

    public static String getValueByCity(Map<String, String> map , String key) {
        String value = map.get(key);
        if (StrUtil.isBlank(value)) {
            throw new ServiceException(StrUtil.format("未配置({})属性,请检查ivvs_properties表" , key));
        }
        return value;
    }

    public static String getValueByCity(String cityCode , String key) {
        String value = getCityProperty(cityCode).get(key);
        if (StrUtil.isBlank(value)) {
            throw new ServiceException(StrUtil.format("该城市({})未配置({})属性,请检查ivvs_properties表" , cityCode , key));
        }
        return value;
    }

    /***
     * 获取当前城市默认wsdl配置
     * @param cityCode 城市代码
     * @return
     */
    public static WsdlProperties getWsdlProperties(String cityCode) {
        Map<String, String> map = getCityProperty(cityCode);
        WsdlProperties properties = new WsdlProperties();
        properties.setJkxlh(getValueByCity(map , PropertyKeyEnum.WSDL_JKXLH.getKey()));
        properties.setXtlb(getValueByCity(map , PropertyKeyEnum.WSDL_XTLB.getKey()));
        properties.setJkid(getValueByCity(map , PropertyKeyEnum.WSDL_JKID.getKey()));
        properties.setUrl(getValueByCity(map , PropertyKeyEnum.WSDL_URL.getKey()));
        return properties;
    }

    /***
     * 获取当前城市默认ftp配置
     * @param cityCode 城市代码
     * @return
     */
    public static FtpProperties getFtpProperties(String cityCode) {
        FtpProperties properties = new FtpProperties();
        Map<String, String> map = getCityProperty(cityCode);
        properties.setHost(getValueByCity(map , PropertyKeyEnum.FTP_HOST.getKey()));
        properties.setPort(Integer.valueOf(getValueByCity(map , PropertyKeyEnum.FTP_PORT.getKey())));
        properties.setUsername(getValueByCity(map , PropertyKeyEnum.FTP_USERNAME.getKey()));
        properties.setPassword(getValueByCity(map , PropertyKeyEnum.FTP_PASSWORD.getKey()));
        properties.setPath(getValueByCity(map , PropertyKeyEnum.FTP_PATH.getKey()));
        return properties;
    }

    /***
     * 获取当前城市代码
     * @return
     */
    public static String getCityCode() {
        return BASE_PROPS_MAP.get(PropertyKeyEnum.CITY_CODE.getKey());
    }

    /***
     * nginx代理地址
     * @return
     */
    public static String getProxyAddress(String cityCode) {
        return getValueByCity(cityCode , PropertyKeyEnum.PROXY_ADDRESS.getKey());
    }

    /***
     * kafka启用开关
     * @return
     */
    public static Boolean getKafkaEnable() {
        return Boolean.valueOf(BASE_PROPS_MAP.get(PropertyKeyEnum.KAFKA_ENABLE.getKey()));
    }

    /***
     * 源图代理路径
     * @return
     */
    public static String getSourceWebPath() {
        return BASE_PROPS_MAP.get(PropertyKeyEnum.SOURCE_WEB_PATH.getKey());
    }

    /***
     * 添加指定城市属性
     * @param key
     * @param value
     */
    public static void putCityProps(String key , Map value) {
        CITY_PROPS_MAP.put(key , value);
    }


    /***
     * 获取指定城市属性
     * @param key
     * @return
     */
    public static Map<String, String> getCityProps(String key) {
        return CITY_PROPS_MAP.get(key);
    }


    /***
     * 清空与城市绑定属性
     */
    public static void clearCityPropsMap() {
        synchronized (CITY_PROPS_MAP) {
            CITY_PROPS_MAP.clear();
        }
    }

    /***
     * 添加基本属性
     * @param key
     * @param value
     */
    public static void putBaseProps(String key , String value) {
        BASE_PROPS_MAP.put(key , value);
    }

    /***
     * 获取基本属性
     * @param key
     * @return
     */
    public static String getBaseProps(String key) {
        return BASE_PROPS_MAP.get(key);
    }

    /***
     * 清空基本属性
     */
    public static void clearBasePropsMap() {
        synchronized (BASE_PROPS_MAP) {
            BASE_PROPS_MAP.clear();
        }
    }

    public static void printCityProps() {
        for (Map.Entry<String, Map<String, String>> entry : CITY_PROPS_MAP.entrySet()) {
            log.info("-------------------");
            for (Map.Entry<String, String> entry2 : entry.getValue().entrySet()) {
                log.info("properties is {} >> {} >> {}" , entry.getKey() , entry2.getKey() , entry2.getValue());
            }
        }
    }

    public static void printBaseProps() {
        for (Map.Entry<String, String> entry : BASE_PROPS_MAP.entrySet()) {
            log.info("properties is {} >> {} >> {}" , "base" , entry.getKey() , entry.getValue());
        }
    }
}
