package com.emdata.common.enums;

public enum CityCodeEnum {
    //city code
    City_130300("130300" , "秦皇岛"),
    City_130600("130600" , "保定"),

    City_140200("140200" , "大同"),
    City_140700("140700" , "介休"),

    City_320200("320200" , "无锡"),
    City_320500("320500" , "苏州"),
    City_320581("320581" , "常熟"),
    City_320700("320700" , "连云港"),
    City_330100("330100" , "杭州"),
    City_330500("330500" , "湖州"),

    City_330700("330700" , "金华"),
    City_330782("330782" , "义乌"),

    City_360900("360900" , "宜春"),
    City_370100("370100" , "济南"),

    City_420100("420100" , "武汉"),
    City_441300("441300" , "惠州"),

    City_500100("500100" , "重庆"),


    City_450100("450100" , "南宁"),

    City_370300("370300" , "淄博"),

    City_320300("320300" , "徐州"),

    City_140400("140400" , "长治"),

    City_130800("130800" , "承德"),


    City_230600("230600" , "大庆"),

    City_370829("370829" , "嘉祥"),

    City_410100("410100" , "郑州"),

    City_410122("410122" , "中牟县"),


    City_430200("430200" , "株洲"),

    City_410825("410825" , "温县"),





    ;


    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    CityCodeEnum(String code , String name) {
        this.code = code;
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

    public static String getName(String cityCode) {
        for (CityCodeEnum c : CityCodeEnum.values()) {
            if (c.code.equals(cityCode)) {
                return c.name;
            }
        }
        return null;
    }

}
