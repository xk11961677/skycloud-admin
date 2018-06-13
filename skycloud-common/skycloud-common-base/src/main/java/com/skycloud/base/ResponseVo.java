package com.skycloud.base;


import com.skycloud.base.enums.FailureCodeEnum;
import com.skycloud.base.enums.ResultCodeEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 通用返回结果类
 * @param <T>
 */
public class ResponseVo<T> implements Serializable {

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

    public static <T> ResponseVo<T> ok(T data) {
        ResponseVo result = new ResponseVo();
        result.setResultCode(ResultCodeEnum.SUCCESS.getCode());
        result.setData(data);
        return result;
    }

    public static <T> ResponseVo<T> ok(T data, ResponseVo result) {
        result.setResultCode(ResultCodeEnum.SUCCESS.getCode());
        result.setData(data);
        return result;
    }

    public static ResponseVo ok() {
        ResponseVo result = new ResponseVo();
        result.setResultCode(ResultCodeEnum.SUCCESS.getCode());
        return result;
    }

    public static <T> ResponseVo<T> error() {
        ResponseVo result = new ResponseVo();
        result.setResultCode(ResultCodeEnum.FAILURE.getCode());
        result.setFailureCode(FailureCodeEnum.SERVICE_EXCEPTION.getCode());
        result.setFailureMessage(FailureCodeEnum.SERVICE_EXCEPTION.getMsg());
        return result;
    }

    public static <T> ResponseVo<T> error(String failureCode) {
        ResponseVo result = new ResponseVo();
        result.setResultCode(ResultCodeEnum.FAILURE.getCode());
        result.setFailureCode(failureCode);
        return result;
    }

    public static <T> ResponseVo<T> error(String failureCode, String failureMessage) {
        ResponseVo result = new ResponseVo();
        result.setResultCode(ResultCodeEnum.FAILURE.getCode());
        result.setFailureCode(failureCode);
        result.setFailureMessage(failureMessage);
        return result;
    }

    public static <T> ResponseVo<T> error(FailureCodeEnum failureCodeEnum) {
        ResponseVo result = new ResponseVo();
        result.setResultCode(ResultCodeEnum.FAILURE.getCode());
        result.setFailureCode(failureCodeEnum.getCode());
        result.setFailureMessage(failureCodeEnum.getMsg());
        return result;
    }

    public boolean isOk() {
        return ResultCodeEnum.SUCCESS.getCode().equals(getResultCode());
    }

    public String toString() {
        return "ResponseVo [resultCode=" + this.resultCode + ", failureCode=" + this.failureCode + ", failureMessage=" + this.failureMessage + ", data=" + this.data + "]";
    }
}
