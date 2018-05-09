package com.skycloud.auth.client.feign;

import com.skycloud.auth.client.client.AuthClient;
import com.skycloud.base.common.base.Result;
import com.skycloud.auth.client.config.AuthProperties;
import com.skycloud.auth.client.annotation.IgnoreAuthClientURL;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author sky
 * @description
 **/
@Slf4j
@Configuration
public class AuthFeignInterceptor implements RequestInterceptor {

    @Autowired
    @SuppressWarnings("all")
    private AuthClient authClient;

    @Autowired
    private AuthProperties authProperties;


    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.info("================>>:{} auth feign interceptor apply begin ");
        IgnoreAuthClientURL ignoreAuthClientURL = AuthFeignContext.instance.get();
        if(ignoreAuthClientURL != null) {
            log.info("auth feign interceptor ignore url:{}",requestTemplate.method());
            return;
        }
        String clientId = authProperties.getClientId();

        String secret = authProperties.getSecret();

        Result<String> accessToken = authClient.getAccessToken(clientId, secret);

        requestTemplate.header(authProperties.getTokenHeader(), accessToken.getData());

        log.info("================>>:{} auth feign interceptor apply end ");
    }
}
