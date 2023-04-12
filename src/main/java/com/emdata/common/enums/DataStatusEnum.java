package com.emdata.common.enums;

public enum DataStatusEnum {
    NORMAL(0,"正常"),
    INVALID(2,"失效"),
    ;


    DataStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    private Integer code;
    private String desc;
}
