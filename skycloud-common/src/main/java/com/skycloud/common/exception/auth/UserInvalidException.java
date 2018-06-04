package com.skycloud.common.exception.auth;


import com.skycloud.common.constants.CommonConstants;
import com.skycloud.common.exception.BaseException;

/**
 */
public class UserInvalidException extends BaseException {
    public UserInvalidException(String message) {
        super(message, CommonConstants.EX_USER_PASS_INVALID_CODE);
    }
}
