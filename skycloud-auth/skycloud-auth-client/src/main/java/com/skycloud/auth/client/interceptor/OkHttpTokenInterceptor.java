package com.skycloud.auth.client.interceptor;

import com.skycloud.auth.client.configuration.ClientAuthConfiguration;
import com.skycloud.auth.client.configuration.UserAuthConfiguration;
import com.skycloud.auth.client.util.ServiceAuthUtil;
import com.skycloud.base.BaseContextHandler;
import com.skycloud.base.constant.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Objects;


/**
 * @author ace
 */
@Component
@Slf4j
public class OkHttpTokenInterceptor implements Interceptor {

    @Resource
    private ServiceAuthUtil serviceAuthUtil;

    @Resource
    private ClientAuthConfiguration clientAuthConfiguration;

    @Resource
    private UserAuthConfiguration userAuthConfiguration;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request newRequest = null;
        String url = Objects.toString(chain.request().url());
        if (url.contains("client/token") || url.contains("/client/myClient")) {
            newRequest = chain.request()
                    .newBuilder()
                    .header(userAuthConfiguration.getUserTokenHeader(), BaseContextHandler.getToken())
                    .build();
        } else {
            newRequest = chain.request()
                    .newBuilder()
                    .header(userAuthConfiguration.getUserTokenHeader(), BaseContextHandler.getToken())
                    .header(clientAuthConfiguration.getClientTokenHeader(), serviceAuthUtil.acquireToken())
                    .build();
        }
        Response response = chain.proceed(newRequest);
        if (HttpStatus.FORBIDDEN.value() == response.code()) {
            if (response.body().string().contains(String.valueOf(CommonConstants.EX_CLIENT_INVALID_CODE))) {
                log.info("Client Token Expire,Retry to request...");
//                authClient.refreshClientToken();
                newRequest = chain.request()
                        .newBuilder()
                        .header(userAuthConfiguration.getUserTokenHeader(), BaseContextHandler.getToken())
                        .header(clientAuthConfiguration.getClientTokenHeader(), serviceAuthUtil.acquireToken())
                        .build();
                response = chain.proceed(newRequest);
            }
        }
        return response;
    }
}
