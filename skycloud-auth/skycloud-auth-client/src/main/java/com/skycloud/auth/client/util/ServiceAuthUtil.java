package com.skycloud.auth.client.util;

import com.skycloud.auth.client.client.AuthClient;
import com.skycloud.auth.client.configuration.ClientConfiguration;
import com.skycloud.common.base.Result;
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
    private AuthClient authClient;

    @Resource
    private ClientConfiguration clientConfiguration;


    public String acquireToken() {
        Result<java.lang.String> token = authClient.getAccessToken(clientConfiguration.getClientId(), clientConfiguration.getSecret());
        String data = Objects.toString(ofNullable(token).orElse(new Result<>()).getData());
        return data;
    }


}
