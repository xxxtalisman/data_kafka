package com.emdata.common.enums;

public enum DBTypeEnum {
    DB_IVVS("db_ivvs");

    private String value;

    DBTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
