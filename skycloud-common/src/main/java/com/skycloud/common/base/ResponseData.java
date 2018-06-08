package com.skycloud.common.base;


import com.skycloud.common.enumcode.FailureCodeEnum;
import com.skycloud.common.enumcode.ResultCodeEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 通用返回结果类
 * @param <T>
 */
public class ResponseData<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Setter
    @Getter
    private String resultCode;

    @Setter
    @Getter
    private String failureCode;

    @Setter
    @Getter
    private String failureMessage;

    @Setter
    @Getter
    private T data;

    public static <T> ResponseData<T> ok(T data) {
        ResponseData result = new ResponseData();
        result.setResultCode(ResultCodeEnum.SUCCESS.getCode());
        result.setData(data);
        return result;
    }

    public static <T> ResponseData<T> ok(T data, ResponseData result) {
        result.setResultCode(ResultCodeEnum.SUCCESS.getCode());
        result.setData(data);
        return result;
    }

    public static ResponseData ok() {
        ResponseData result = new ResponseData();
        result.setResultCode(ResultCodeEnum.SUCCESS.getCode());
        return result;
    }

    public static <T> ResponseData<T> error() {
        ResponseData result = new ResponseData();
        result.setResultCode(ResultCodeEnum.FAILURE.getCode());
        result.setFailureCode(FailureCodeEnum.SERVICE_EXCEPTION.getCode());
        result.setFailureMessage(FailureCodeEnum.SERVICE_EXCEPTION.getMsg());
        return result;
    }

    public static <T> ResponseData<T> error(String failureCode) {
        ResponseData result = new ResponseData();
        result.setResultCode(ResultCodeEnum.FAILURE.getCode());
        result.setFailureCode(failureCode);
        return result;
    }

    public static <T> ResponseData<T> error(String failureCode, String failureMessage) {
        ResponseData result = new ResponseData();
        result.setResultCode(ResultCodeEnum.FAILURE.getCode());
        result.setFailureCode(failureCode);
        result.setFailureMessage(failureMessage);
        return result;
    }

    public static <T> ResponseData<T> error(FailureCodeEnum failureCodeEnum) {
        ResponseData result = new ResponseData();
        result.setResultCode(ResultCodeEnum.FAILURE.getCode());
        result.setFailureCode(failureCodeEnum.getCode());
        result.setFailureMessage(failureCodeEnum.getMsg());
        return result;
    }

    public boolean isOk() {
        return ResultCodeEnum.SUCCESS.getCode().equals(getResultCode());
    }

    public String toString() {
        return "ResponseData [resultCode=" + this.resultCode + ", failureCode=" + this.failureCode + ", failureMessage=" + this.failureMessage + ", data=" + this.data + "]";
    }
}
