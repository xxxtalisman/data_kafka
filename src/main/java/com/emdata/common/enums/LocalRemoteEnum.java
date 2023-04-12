package com.emdata.common.enums;

/**
 * @Description: 图片地址为本地|远程
 * @Author: zhangyanjun
 * @CreateDate: 2019/6/13 9:13
 * @Version: 1.0
 */
public enum LocalRemoteEnum {

    LOCAL(0,"本地"),
    REMOTE(1,"远程");

    private Integer code;
    private String msg;

    LocalRemoteEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }}
