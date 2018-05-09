package com.skycloud.base.oauth.server.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientInvalidException extends RuntimeException{

    public ClientInvalidException() {
        super();
    }

    public ClientInvalidException(String message) {
        super(message);
        log.error(message);
    }
}
