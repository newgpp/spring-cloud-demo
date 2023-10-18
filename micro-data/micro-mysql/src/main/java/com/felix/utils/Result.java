package com.felix.utils;


import com.felix.enums.RestCode;

/**
 * @author felix
 * @description some desc
 * @date 2023/6/27 8:31
 */
public class Result<T> {

    private int code;

    private String errMsg;

    private T value;

    public static <T> Result<T> success(T t) {
        Result<T> result = new Result<>();
        result.setCode(RestCode.SUCCESS);
        result.setValue(t);
        return result;
    }

    public static <T> Result<T> fail(int code, String errMsg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setErrMsg(errMsg);
        return result;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
