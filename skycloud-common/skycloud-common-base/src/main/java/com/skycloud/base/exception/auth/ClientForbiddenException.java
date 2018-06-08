package com.skycloud.base.exception.auth;


import com.skycloud.base.constant.CommonConstants;
import com.skycloud.base.exception.BussinessException;

/**
 */
public class ClientForbiddenException extends BussinessException {
    public ClientForbiddenException(String message) {
        super(message, CommonConstants.EX_CLIENT_FORBIDDEN_CODE);
    }

}
