package com.skycloud.auth.client.feign;

import com.skycloud.auth.client.client.AuthClient;
import com.skycloud.common.base.Result;
import com.skycloud.auth.client.configuration.ClientConfiguration;
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

    @SuppressWarnings("all")
    @Autowired
    private ClientConfiguration clientConfiguration;


    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.info("================>>:{} auth feign interceptor apply begin ");
        IgnoreAuthClientURL ignoreAuthClientURL = AuthFeignContext.getIgnoreAuthClientURL();
        if(ignoreAuthClientURL != null) {
            log.info("auth feign interceptor ignore url:{}",requestTemplate.method());
            return;
        }
        String clientId = clientConfiguration.getClientId();

        String secret = clientConfiguration.getSecret();

        Result<String> accessToken = authClient.getAccessToken(clientId, secret);

        requestTemplate.header(clientConfiguration.getClientTokenHeader(), accessToken.getData());

        log.info("================>>:{} auth feign interceptor apply end ");
    }
}
