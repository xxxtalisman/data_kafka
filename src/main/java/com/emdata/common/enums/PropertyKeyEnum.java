package com.emdata.common.enums;

/**
 * @Date: 2019/12/17 8:48
 * @Version: 1.0
 * @Author: pengqingfeng
 * @Description: ivvs_properties表中property_key
 */
public enum PropertyKeyEnum {

    /*******默认ftp********/
    FTP_HOST("ftp.host" , "ftp地址"),
    FTP_PASSWORD("ftp.password" , "ftp密码"),
    FTP_PATH("ftp.path" , "ftp路径"),
    FTP_PORT("ftp.port" , "ftp端口"),
    FTP_USERNAME("ftp.username" , "ftp用户名"),


    /*******苏州视频ftp地址********/
    FTP_VIDEO_HOST("ftp.video.host" , "ftp地址(视频)"),
    FTP_VIDEO_PASSWORD("ftp.video.password" , "ftp密码(视频)"),
    FTP_VIDEO_PATH("ftp.video.path" , " ftp路径(视频)"),
    FTP_VIDEO_PORT("ftp.video.port" , "ftp端口(视频)"),
    FTP_VIDEO_USERNAME("ftp.video.username" , "ftp用户名(视频)"),

    /*******集指属性********/
    WSDL_JKID("wsdl.jkid" , "接口ID"),
    WSDL_JKXLH("wsdl.jkxlh" , "接口序列号"),
    WSDL_URL("wsdl.url" , "接口地址"),
    WSDL_XTLB("wsdl.xtlb" , "系统类别"),

    /*******南宁用********/
    API_URL("api.url" , "接口地址"),
    API_YHZH("api.yhzh" , "用户密码"),
    API_YHMM("api.yhmm" , "用户密码"),
    API_JKID("api.jkid" , "接口ID"),
    API_TYPE_READ("api.type.read" , "查询（类型）"),
    API_TYPE_WRITE("api.type.write" , "写入（类型）"),


    /**************内部平台参数*******************/
    PROXY_ADDRESS("proxy.address" , "nginx代理地址"),


    /*******公共属性********/
    CITY_CODE("city.code" , "当前城市代码"),
    KAFKA_ENABLE("kafka.enable" , "kafka启用开关（启用时会在当前城市代码的文件夹下读取配置文件，没有则报错）"),

    SOURCE_WEB_PATH("source.web.path" , "源图代理路径");


    private String key;
    private String name;


    PropertyKeyEnum(String key , String name) {
        this.key = key;
        this.name = name;
    }


    public static boolean contain(String cityCode) {
        for (CityCodeEnum deviceName : CityCodeEnum.values()) {
            if (deviceName.getCode().equals(cityCode)) {
                return true;
            }
        }
        return false;
    }

    public static String getName(String key) {
        for (PropertyKeyEnum c : PropertyKeyEnum.values()) {
            if (c.key.equals(key)) {
                return c.name;
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
}
