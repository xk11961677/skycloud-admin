package com.skycloud.auth.client.feign;

import com.skycloud.auth.client.annotation.IgnoreAuthClientURL;

/**
 * @author sky
 */
public class AuthFeignContext {

    public final static ThreadLocal<IgnoreAuthClientURL> instance = new ThreadLocal<>();

}
