package com.emdata.common.enums;

public enum AccessStateEnum {
    ENABLE("1","启用"),
    UNABLE("2","禁用"),
    ;


    AccessStateEnum(String code, String desc) {
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
