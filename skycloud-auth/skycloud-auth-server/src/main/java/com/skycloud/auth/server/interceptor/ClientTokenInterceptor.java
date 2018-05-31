package com.skycloud.auth.server.interceptor;

import com.skycloud.auth.server.configuration.ClientAuthConfiguration;
import com.skycloud.auth.server.service.AuthCService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 */
public class ClientTokenInterceptor implements RequestInterceptor {

    @Autowired
    private ClientAuthConfiguration clientAuthConfiguration;

    @Autowired
    private AuthCService authCService;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        try {
            requestTemplate.header(clientAuthConfiguration.getClientTokenHeader(), authCService.apply(clientAuthConfiguration.getClientId(), clientAuthConfiguration.getSecret()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
