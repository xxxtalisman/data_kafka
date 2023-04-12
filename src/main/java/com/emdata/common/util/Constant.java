package com.emdata.common.util;

/**
 * @Date: 2019/12/12 17:43
 * @Version: 1.0
 * @Author: pengqingfeng
 * @Description:
 */
public class Constant {


    /*###########kafka topic name##############*/

    /***
     * 株洲回写topic name
     */
    public static final String TOPIC_NAME_PRODUCE_430200 = "etl-wf-data";

    /***
     * 曲靖回写topic name
     */
    public static final String TOPIC_NAME_PRODUCE_530300= "ai_review_result_topic";

    /*###########kafka topic name##############*/

    /***
     * suffix
     */
    public static final String SUFFIX_IMAGE = ".jpg";
    public static final String SUFFIX_VIDEO = ".mp4";

    /***
     * 集指接口ID
     */
    public static final String JKID_60W01 = "60W01";
    public static final String JKID_60W02 = "60W02";

    /**
     * 集指返回成功code
     */
    public static final String JZ_SUCCESS_CODE = "1";


    public static final String SOURCE_DATA = "src";
    public static final String SOURCE_DATA_MORE = "more";


    public static final String SEND_TARGET_PREFIX = "sendTarget";


    public static final String DATA_TARGET_PREFIX = "dataTarget";


//    /************************ivvs_properties表中property_key**********************************************/
//    /*******默认ftp********/
//    public static final String KEY_FTP_HOST = "ftp.host";
//    public static final String KEY_FTP_PASSWORD = "ftp.password";
//    public static final String KEY_FTP_PATH = "ftp.path";
//    public static final String KEY_FTP_PORT = "ftp.port";
//    public static final String KEY_FTP_USERNAME = "ftp.username";
//
//    /*******苏州视频ftp地址********/
//    public static final String KEY_FTP_VIDEO_HOST = "ftp.video.host";
//    public static final String KEY_FTP_VIDEO_PASSWORD = "ftp.video.password";
//    public static final String KEY_FTP_VIDEO_PATH = "ftp.video.path";
//    public static final String KEY_FTP_VIDEO_PORT = "ftp.video.port";
//    public static final String KEY_FTP_VIDEO_USERNAME = "ftp.video.username";
//
//    /*******集指属性********/
//    public static final String KEY_WSDL_JKID = "wsdl.jkid";
//    public static final String KEY_WSDL_JKXLH = "wsdl.jkxlh";
//    public static final String KEY_WSDL_URL = "wsdl.url";
//    public static final String KEY_WSDL_XTLB = "wsdl.xtlb";
//
//    /*******南宁用********/
//    public static final String KEY_API_URL = "api.url";
//    public static final String KEY_API_YHZH = "api.yhzh";
//    public static final String KEY_API_YHMM = "api.yhmm";
//    public static final String KEY_API_JKID = "api.jkid";
//    public static final String KEY_API_TYPE_READ = "api.type.read";
//    public static final String KEY_API_TYPE_WRITE = "api.type.write";
//
//
//    public static final String KEY_COMMON_CITY_CODE= "city.code";
//    public static final String KEY_COMMON_KAFKA_ENABLE= "kafka.enable";
//
//    /************************ivvs_properties表中property_key**********************************************/
}
