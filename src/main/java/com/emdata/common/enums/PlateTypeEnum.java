package com.emdata.common.enums;

/**
 * @Date: 2019/7/18 16:40
 * @Version: 1.0
 * @Author: pengqingfeng
 * @Description:
 */
public enum PlateTypeEnum {
    TYPE_01("01" , "大型汽车"),
    TYPE_02("02" , "小型汽车"),
    TYPE_03("03" , "使馆汽车"),
    TYPE_04("04" , "领馆汽车"),
    TYPE_05("05" , "境外汽车"),
    TYPE_06("06" , "外籍汽车"),
    TYPE_07("07" , "普通摩托车"),
    TYPE_08("08" , "轻便摩托车"),
    TYPE_09("09" , "使馆摩托车"),
    TYPE_10("10" , "领馆摩托车"),
    TYPE_11("11" , "境外摩托车"),
    TYPE_12("12" , "外籍摩托车"),
    TYPE_13("13" , "低速车"),
    TYPE_14("14" , "拖拉机"),
    TYPE_15("15" , "挂车"),
    TYPE_16("16" , "教练汽车"),
    TYPE_17("17" , "教练摩托车"),
    TYPE_18("18" , "试验汽车"),
    TYPE_19("19" , "试验摩托车"),
    TYPE_20("20" , "临时入境汽车"),
    TYPE_21("21" , "临时入境摩托车"),
    TYPE_22("22" , "临时行驶车"),
    TYPE_23("23" , "警用汽车"),
    TYPE_24("24" , "警用摩托"),
    TYPE_25("25" , "原农机号牌"),
    TYPE_26("26" , "香港入出境车"),
    TYPE_27("27" , "澳门入出境车"),
    TYPE_31("31" , "武警号牌"),
    TYPE_32("32" , "军队号牌"),
    TYPE_33("33" , "应急号牌"),
    TYPE_41("41" , "无号牌"),
    TYPE_42("42" , "假号牌"),
    TYPE_43("43" , "挪用号牌"),
    TYPE_51("51" , "大型新能源汽车"),
    TYPE_52("52" , "小型新能源汽车"),
    TYPE_99("99" , "其他号牌");

    // 成员变量
    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    // 构造方法
    private PlateTypeEnum(String code , String name) {
        this.name = name;
        this.code = code;
    }

    // 普通方法
    public static String getName(String code) {
        for (PlateTypeEnum c : PlateTypeEnum.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return "";
    }
    
    public static void main(String[] args) {
		int a = 1;
		int b = Integer.MAX_VALUE;
		int c = a+b;
		System.out.println(c);
	}
}
