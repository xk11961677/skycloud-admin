package com.skycloud.base.exception.auth;


import com.skycloud.base.constant.CommonConstants;
import com.skycloud.base.exception.BussinessException;

/**
 * Created by ace on 2017/9/10.
 */
public class ClientInvalidException extends BussinessException {
    public ClientInvalidException(String message) {
        super(message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}
