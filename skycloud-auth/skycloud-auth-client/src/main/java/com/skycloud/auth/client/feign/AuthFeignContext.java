package com.skycloud.auth.client.feign;

import com.skycloud.auth.client.annotation.IgnoreAuthClientURL;

/**
 * @author sky
 */
public class AuthFeignContext {

    private final ThreadLocal<IgnoreAuthClientURL> threadLocal = new ThreadLocal<>();

    private final static AuthFeignContext instance = new AuthFeignContext();

    private AuthFeignContext() {
    }

    private AuthFeignContext getAuthFeignContext() {
        return instance;
    }


    public static IgnoreAuthClientURL getIgnoreAuthClientURL() {
        return instance.threadLocal.get();
    }

    public static void setIgnoreAuthClientURL(IgnoreAuthClientURL ignoreAuthClientURL) {
        instance.threadLocal.set(ignoreAuthClientURL);
    }


}
