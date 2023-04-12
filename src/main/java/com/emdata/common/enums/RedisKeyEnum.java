package com.emdata.common.enums;

/**
 * @Author: txw
 * @CreateDate: 2019/10/10 14:39
 * @Version: 1.0
 */
public enum RedisKeyEnum {
    DATA_INSERT_ILLEGAL_ACT_RULE("rule:data_insert_illegal_act_rule","数据接入违法行为限制"),
    DATA_INSERT_DEVICE_RULE("rule:data_insert_device_rule","数据接入设备限制"),
    DEFAULT_CITY_CONFIG("sys:data:default_city" , "当前城市配置"),
    ;

    RedisKeyEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    private String key;
    private String desc;

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }}
