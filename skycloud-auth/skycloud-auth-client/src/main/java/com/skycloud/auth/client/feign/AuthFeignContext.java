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


    public static IgnoreClientToken getIgnoreClientToken() {
        return instance.threadLocal.get();
    }

    public static void removeIgnoreClientToken() {
        instance.threadLocal.remove();
    }

    public static void setIgnoreClientToken(IgnoreClientToken ignoreClientToken) {
        instance.threadLocal.set(ignoreClientToken);
    }

    public static IgnoreUserToken getIgnoreUserToken() {
        return instance.tokenLocal.get();
    }

    public static void setIgnoreUserToken(IgnoreUserToken ignoreUserToken) {
        instance.tokenLocal.set(ignoreUserToken);
    }

    public static void removeIgnoreUserToken() {
        instance.tokenLocal.remove();
    }

}
