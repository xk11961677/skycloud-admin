package com.skycloud.common.exception.auth;


import com.skycloud.common.constants.CommonConstants;
import com.skycloud.common.exception.BaseException;

/**
 */
public class UserTokenException extends BaseException {
    public UserTokenException(String message) {
        super(message, CommonConstants.EX_USER_INVALID_CODE);
    }
}
