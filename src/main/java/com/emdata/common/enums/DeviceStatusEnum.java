package com.emdata.common.enums;

public enum DeviceStatusEnum {
    NORMAL((short)1,"正常"),
    INVALID((short)2,"删除"),
    UNABLE((short)3,"禁用"),
    ;

    private Short code;
    private String desc;

    DeviceStatusEnum(Short code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Short getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
