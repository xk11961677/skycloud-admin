package com.skycloud.base.exception.auth;


import com.skycloud.base.constant.CommonConstants;
import com.skycloud.base.exception.BussinessException;

/**
 */
public class UserInvalidException extends BussinessException {
    public UserInvalidException(String message) {
        super(message, CommonConstants.EX_USER_PASS_INVALID_CODE);
    }
}
