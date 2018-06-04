package com.skycloud.common.exception.auth;


import com.skycloud.common.constants.CommonConstants;
import com.skycloud.common.exception.BaseException;

/**
 */
public class ClientTokenException extends BaseException {
    public ClientTokenException(String message) {
        super(message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}
