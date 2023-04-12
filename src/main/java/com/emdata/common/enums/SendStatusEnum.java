package com.emdata.common.enums;

public enum SendStatusEnum {
    SENDING("发送中" , -1),
    DEFAULT("未发送" , 0),
    SUCCESS("发送成功" , 1),
    FAIL("发送失败" , 2);


    // 成员变量
    private String name;
    private int code;

    // 构造方法
    private SendStatusEnum(String name , int code) {
        this.name = name;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static String getName(int value) {
        for (SendStatusEnum item : SendStatusEnum.values()) {
            if (item.code == value) {
                return item.name;
            }
        }
        return null;
    }
}
