package com.xxx.core.model;


import java.io.Serializable;

/**
 * 响应消息类
 */
public class JsonResult implements Serializable {

    public static int SUCCESS_CODE = 1;

    public static int FAIL_CODE = 0;

    public static final String SUCCESS_MESSAGE = "SUCCESS";

    public static final String FAIL_MESSAGE = "FAILED";

    private int code = SUCCESS_CODE;//状态

    private String msg = SUCCESS_MESSAGE;//消息

    private Object data;

    public JsonResult() {
    }

    public JsonResult(Object data) {
        this.data = data;
    }

    public JsonResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResult getFailedResult() {
        return new JsonResult(FAIL_CODE, FAIL_MESSAGE, null);
    }

    public int getCode() {
        return code;
    }

    public JsonResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public JsonResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public JsonResult setData(Object data) {
        this.data = data;
        return this;
    }
}
