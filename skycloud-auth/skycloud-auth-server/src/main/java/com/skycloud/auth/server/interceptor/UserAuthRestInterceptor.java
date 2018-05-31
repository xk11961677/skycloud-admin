package com.skycloud.auth.server.interceptor;

import com.skycloud.api.dto.UserDTO;
import com.skycloud.auth.common.utils.JwtUtil;
import com.skycloud.auth.server.configuration.UserAuthConfiguration;
import com.skycloud.common.base.BaseContextHandler;
import com.skycloud.common.exception.auth.ClientForbiddenException;
import com.skycloud.common.exception.auth.UserTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
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

        UserDTO userDTO = JwtUtil.unsign(header, UserDTO.class);

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
