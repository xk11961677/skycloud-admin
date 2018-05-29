package com.skycloud.auth.client.feign;

import com.skycloud.auth.client.annotation.IgnoreClientToken;
import com.skycloud.auth.client.annotation.IgnoreUserToken;

/**
 * @author sky
 */
public class AuthFeignContext {

    private final ThreadLocal<IgnoreClientToken> threadLocal = new ThreadLocal<>();

    private final ThreadLocal<IgnoreUserToken> tokenLocal = new ThreadLocal<>();

    private final static AuthFeignContext instance = new AuthFeignContext();

    private AuthFeignContext() {
    }

    private AuthFeignContext getAuthFeignContext() {
        return instance;
    }


    public static IgnoreClientToken getIgnoreAuthClientURL() {
        return instance.threadLocal.get();
    }

    public static void setIgnoreAuthClientURL(IgnoreClientToken ignoreAuthClientURL) {
        instance.threadLocal.set(ignoreAuthClientURL);
    }

    public static IgnoreUserToken getIgnoreAuthTokenURL() {
        return instance.tokenLocal.get();
    }

    public static void setIgnoreAuthTokenURL(IgnoreUserToken ignoreAuthTokenURL) {
        instance.tokenLocal.set(ignoreAuthTokenURL);
    }


}
