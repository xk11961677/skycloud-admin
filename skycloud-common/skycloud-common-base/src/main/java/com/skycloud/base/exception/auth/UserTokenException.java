package com.skycloud.base.exception.auth;


import com.skycloud.base.constant.CommonConstants;
import com.skycloud.base.exception.BussinessException;

/**
 */
public class UserTokenException extends BussinessException {
    public UserTokenException(String message) {
        super(message, CommonConstants.EX_USER_INVALID_CODE);
    }
}
