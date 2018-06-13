package com.skycloud.auth.client.util;

import com.skycloud.auth.client.client.AuthApi;
import com.skycloud.auth.client.configuration.ClientAuthConfiguration;
import com.skycloud.base.ResponseVo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * @author sky
 * @description
 * @create 2018-05-30 下午8:52
 **/
@Component
public class ServiceAuthUtil {

    @Resource
    @Lazy
    private AuthApi authApi;

    @Resource
    private ClientAuthConfiguration clientConfiguration;


    public String acquireToken() {
        ResponseVo<String> token = authApi.getAccessToken(clientConfiguration.getClientId(), clientConfiguration.getSecret());
        String data = Objects.toString(ofNullable(token).orElse(new ResponseVo<>()).getData());
        return data;
    }


}
