package com.skycloud.base.exception.auth;


import com.skycloud.base.constant.CommonConstants;
import com.skycloud.base.exception.BussinessException;

/**
 */
public class ClientTokenException extends BussinessException {
    public ClientTokenException(String message) {
        super(message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}
