package com.emdata.common.enums;

public enum DataProcTypeEnum {
    INPUT(1,"数据接入"),
    ALG_PROC(2,"算法处理"),
    HANDLE(3,"人工处理");

    DataProcTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
