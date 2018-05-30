package com.skycloud.auth.client.feign;

import com.skycloud.auth.client.annotation.IgnoreUserToken;
import com.skycloud.auth.client.client.AuthClient;
import com.skycloud.auth.client.configuration.UserAuthConfiguration;
import com.skycloud.common.base.BaseContextHandler;
import com.skycloud.common.base.Result;
import com.skycloud.auth.client.configuration.ClientConfiguration;
import com.skycloud.auth.client.annotation.IgnoreClientToken;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author sky
 * @description
 **/
@Slf4j
@Configuration
public class AuthFeignInterceptor implements RequestInterceptor {

    @Resource
    private AuthClient authClient;

    @Resource
    private ClientConfiguration clientConfiguration;

    @Resource
    private UserAuthConfiguration userAuthConfiguration;


    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.info(" feign interceptor apply begin ");

        IgnoreUserToken ignoreUserToken = AuthFeignContext.getIgnoreUserToken();
        if(ignoreUserToken != null) {
            AuthFeignContext.removeIgnoreUserToken();
        }else {
            String token = BaseContextHandler.getToken();
            requestTemplate.header(userAuthConfiguration.getUserTokenHeader(), token);
            log.info(" feign interceptor ignore user url:{}",requestTemplate.url());
        }

        IgnoreClientToken ignoreClientToken = AuthFeignContext.getIgnoreClientToken();
        if(ignoreClientToken != null) {
            AuthFeignContext.removeIgnoreClientToken();
            log.info(" feign interceptor ignore client url:{}",requestTemplate.url());
            return;
        }

        String clientId = clientConfiguration.getClientId();

        String secret = clientConfiguration.getSecret();

        Result<String> accessToken = authClient.getAccessToken(clientId, secret);

        log.info(" feign interceptor apply access token "+accessToken);

        requestTemplate.header(clientConfiguration.getClientTokenHeader(), accessToken.getData());

        log.info(" feign interceptor apply end ");
    }
}
