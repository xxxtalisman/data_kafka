package com.emdata.common.enums;

public enum DataProcErrEnum {
    FIRST_IMG_EMPTY("100","第一张图片为空"),
    SAVE_FIRST_IMG_FAIL("101","第一张图片保存失败"),
    SAVE_SECOND_IMG_FAIL("101","第二张图片保存失败"),
    SAVE_THIRD_IMG_FAIL("101","第三张图片保存失败"),
    SAVE_FOURTH_IMG_FAIL("101","第四张图片保存失败"),
    BEANS_EXCEPTION("102","从oracle实体拷贝数据到SerInputdata失败"),
    WFSJ_EMPTY("103","违法时间为空"),
    WFXW_EMPTY("104","违法行为为空"),
    REPEAT_DATA("105","重复数据"),
    SBBH_EMPTY("106","设备编号为空"),
    HPHM_EMPTY("107","号牌号码为空"),
    SBBH_LIMIT_INSERT("108","该设备数据限制接入"),
    WFXW_LIMIT_INSERT("109","该违法行为数据限制接入"),
    KAFKA_CONFIG_EMPTY("110","该城市kafka配置项不存在,不是kafka接入方式,请联系管理员."),

    SYSTEM_ERR("999","系统错误"),
    ;


    DataProcErrEnum(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private String errCode;
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
