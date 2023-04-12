package com.emdata.common.enums;

public enum ReceiveStateEnum {
    ENABLE("0","开启"),
    UNABLE("1","关闭"),
    ;


    ReceiveStateEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private String code;
    private String desc;
}
