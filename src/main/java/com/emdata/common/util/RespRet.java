package com.emdata.common.util;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class RespRet<T> implements Serializable {


    public static int SUCCESS = 1;
    public static int FAIL = 2;

    private int code;
    private T data;
    private String msg;

    public RespRet() {
    }

    public static <T> RespRet<T> ok() {
        return restResult(null , SUCCESS);
    }


    public static <T> RespRet<T> ok(T data) {
        return restResult(data , SUCCESS);
    }

    public static <T> RespRet<T> failed(String msg) {
        return restResult(null , FAIL , msg);
    }

    public static <T> RespRet<T> restResult(T data , int code) {
        return restResult(data , code , null);
    }

    private static <T> RespRet<T> restResult(T data , int code , String msg) {
        RespRet<T> apiResult = new RespRet();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }
}
