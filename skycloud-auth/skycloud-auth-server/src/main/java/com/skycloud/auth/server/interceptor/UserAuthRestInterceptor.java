package com.skycloud.auth.server.interceptor;

import com.skycloud.auth.common.utils.JwtUtil;
import com.skycloud.auth.server.configuration.UserAuthConfiguration;
import com.skycloud.base.BaseContextHandler;
import com.skycloud.base.exception.auth.UserTokenException;
import com.skycloud.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sky
 * 拦截controller从request获取access-token,根据id获取serviceName与当前项目比对
 **/
@Slf4j
@Configuration
public class UserAuthRestInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private UserAuthConfiguration userAuthConfiguration;

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
        String header = request.getHeader(userAuthConfiguration.getUserTokenHeader());

        UserDto userDTO = JwtUtil.unsign(header, UserDto.class);

        if (userDTO != null) {
            BaseContextHandler.setToken(header);
            BaseContextHandler.setName(userDTO.getNickName());
            BaseContextHandler.setUserID(userDTO.getId() + "");
            BaseContextHandler.setUsername(userDTO.getName());
            super.preHandle(request, response, handler);
        }
        throw new UserTokenException("User is Forbidden!");
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContextHandler.remove();
        super.afterCompletion(request, response, handler, ex);
    }


}
