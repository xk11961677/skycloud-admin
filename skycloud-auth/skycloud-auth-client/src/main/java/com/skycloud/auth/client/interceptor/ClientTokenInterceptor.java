package com.skycloud.auth.client.interceptor;

import com.skycloud.auth.client.client.AuthClient;
import com.skycloud.auth.client.configuration.ClientConfiguration;
import com.skycloud.common.base.Result;
import com.skycloud.auth.client.annotation.IgnoreAuthClientURL;
import com.skycloud.auth.common.dto.AuthClientDTO;
import com.skycloud.auth.common.dto.AuthJwtDTO;
import com.skycloud.auth.common.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author sky
 * 拦截controller从request获取access-token,根据id获取serviceName与当前项目比对
 *
 **/
@Slf4j
@Configuration
public class ClientTokenInterceptor extends HandlerInterceptorAdapter {

    @SuppressWarnings("all")
    @Autowired
    private ClientConfiguration clientConfiguration;

    @Autowired
    @SuppressWarnings("all")
    private AuthClient authClient;

    @Value(value = "${spring.application.name}")
    private String appName;

    /**
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("==========>>:{}preHandle");
        boolean flag = false;

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        IgnoreAuthClientURL clazzAnnotation = handlerMethod.getBean().getClass().getAnnotation(IgnoreAuthClientURL.class);

        IgnoreAuthClientURL methodAnnotation = handlerMethod.getMethodAnnotation(IgnoreAuthClientURL.class);

        if(methodAnnotation != null || clazzAnnotation != null) {
            return true;
        }

        String header = request.getHeader(clientConfiguration.getClientTokenHeader());

        AuthJwtDTO authJwtDTO = JwtUtil.unsign(header, AuthJwtDTO.class);

        if(authJwtDTO != null) {

            Result<List<AuthClientDTO>> result = authClient.getAllowClient(authJwtDTO.getId() + "");

            if(result !=null && result.getData() != null) {
                flag = result.getData().parallelStream().filter(authClientDTO -> authClientDTO.getCode().equals(appName)).findFirst().isPresent();
            }
        }
        return flag;
    }
}
