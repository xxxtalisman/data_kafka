package com.emdata.common.enums;


public enum SendTargetModeEnum {

    OTHER(-1 , null),

    //集指
    COMMAND_PLATFORM(1 , "CommandPlatform");

    private int code;

    private String beanName;

    private SendTargetModeEnum(int code , String beanName) {
        this.code = code;
        this.beanName = beanName;
    }


    public int getCode() {
        return code;
    }


    public static String getBeanName(int code) {
        for (SendTargetModeEnum c : SendTargetModeEnum.values()) {
            if (c.code == code) {
                return c.beanName;
            }
        }
        return null;
    }

}
