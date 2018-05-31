package com.skycloud.auth.client.interceptor;

import com.skycloud.api.dto.UserDTO;
import com.skycloud.auth.client.annotation.IgnoreUserToken;
import com.skycloud.auth.client.client.AuthApi;
import com.skycloud.auth.client.configuration.UserAuthConfiguration;
import com.skycloud.auth.common.utils.JwtUtil;
import com.skycloud.common.base.BaseContextHandler;
import com.skycloud.common.exception.auth.UserTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sky
 * 拦截controller从request获取access-token,根据id获取serviceName与当前项目比对
 **/
@Slf4j
@Configuration
public class UserAuthRestInterceptor extends HandlerInterceptorAdapter {

    @SuppressWarnings("all")
    @Autowired
    private UserAuthConfiguration userAuthConfiguration;

    @Autowired
    @SuppressWarnings("all")
    private AuthApi authClient;

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
        log.info("interceptor user token begin ");
        boolean flag = false;

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        IgnoreUserToken clazzAnnotation = handlerMethod.getBean().getClass().getAnnotation(IgnoreUserToken.class);

        IgnoreUserToken methodAnnotation = handlerMethod.getMethodAnnotation(IgnoreUserToken.class);

        String header = request.getHeader(userAuthConfiguration.getUserTokenHeader());

        UserDTO userDTO = JwtUtil.unsign(header, UserDTO.class);

        if (userDTO != null) {
            BaseContextHandler.setToken(header);
            BaseContextHandler.setName(userDTO.getNickName());
            BaseContextHandler.setUserID(userDTO.getId()+"");
            BaseContextHandler.setUsername(userDTO.getName());
            flag = true;
        }

        if (methodAnnotation != null || clazzAnnotation != null) {
            flag = true;
        }
        if(flag) {
            return super.preHandle(request,response,handler);
        }
        throw new UserTokenException("User is Forbidden!");

    }
}
