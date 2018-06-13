package com.skycloud.auth.client.interceptor;

import com.skycloud.auth.client.client.AuthApi;
import com.skycloud.auth.client.configuration.ClientAuthConfiguration;
import com.skycloud.auth.client.annotation.IgnoreClientToken;
import com.skycloud.auth.common.dto.AuthClientDTO;
import com.skycloud.auth.common.dto.AuthJwtDTO;
import com.skycloud.auth.common.utils.JwtUtil;
import com.skycloud.base.ResponseVo;
import com.skycloud.base.exception.auth.ClientForbiddenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author sky
 * 拦截controller从request获取access-token,根据id获取serviceName与当前项目比对
 **/
@Slf4j
@Configuration
public class ServiceAuthRestInterceptor extends HandlerInterceptorAdapter {

    @SuppressWarnings("all")
    @Autowired
    private ClientAuthConfiguration clientAuthConfiguration;

    @Autowired
    @SuppressWarnings("all")
    private AuthApi authApi;

    @Value(value = "${spring.application.name}")
    private String appName;

    /**
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(" interceptor begin ");

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        IgnoreClientToken clazzAnnotation = handlerMethod.getBean().getClass().getAnnotation(IgnoreClientToken.class);

        IgnoreClientToken methodAnnotation = handlerMethod.getMethodAnnotation(IgnoreClientToken.class);

        if (methodAnnotation != null || clazzAnnotation != null) {
            return super.preHandle(request, response, handler);
        }

        String header = request.getHeader(clientAuthConfiguration.getClientTokenHeader());

        AuthJwtDTO authJwtDTO = JwtUtil.unsign(header, AuthJwtDTO.class);

        if (authJwtDTO != null) {

            ResponseVo<List<AuthClientDTO>> result = authApi.getAllowClient(authJwtDTO.getId() + "");

            if (result != null && result.getData() != null && result.getData().parallelStream().filter(authClientDTO -> authClientDTO.getCode().equals(appName)).findFirst().isPresent()) {
                return super.preHandle(request, response, handler);
            }
        }
        throw new ClientForbiddenException("Client is Forbidden!");
    }
}
