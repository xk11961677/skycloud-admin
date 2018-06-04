package com.skycloud.common.exception.auth;


import com.skycloud.common.constants.CommonConstants;
import com.skycloud.common.exception.BaseException;

/**
 */
public class ClientForbiddenException extends BaseException {
    public ClientForbiddenException(String message) {
        super(message, CommonConstants.EX_CLIENT_FORBIDDEN_CODE);
    }

}
