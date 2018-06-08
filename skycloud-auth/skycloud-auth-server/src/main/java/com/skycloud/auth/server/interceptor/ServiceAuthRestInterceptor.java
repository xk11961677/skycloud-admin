package com.skycloud.auth.server.interceptor;

import com.skycloud.auth.common.dto.AuthJwtDTO;
import com.skycloud.auth.common.utils.JwtUtil;
import com.skycloud.auth.server.configuration.ClientAuthConfiguration;
import com.skycloud.auth.server.model.domain.AuthClient;
import com.skycloud.auth.server.service.AuthCService;
import com.skycloud.common.exception.auth.ClientForbiddenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author sky
 * 拦截controller从request获取access-token,根据id获取serviceName与当前项目比对
 *
 **/
@Slf4j
@Configuration
public class ServiceAuthRestInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private ClientAuthConfiguration clientAuthConfiguration;

    @Resource
    private AuthCService authCService;

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

        String header = request.getHeader(clientAuthConfiguration.getClientTokenHeader());

        AuthJwtDTO authJwtDTO = JwtUtil.unsign(header, AuthJwtDTO.class);

        if(authJwtDTO != null) {

            List<AuthClient> allowedClient = authCService.getAllowedClientByServiceId(authJwtDTO.getId() + "");

            if(CollectionUtils.isEmpty(allowedClient) &&
                    allowedClient.parallelStream().filter(authClientDTO -> authClientDTO.getCode().equals(appName)).findFirst().isPresent()) {
                super.preHandle(request,response,handler);
            }
        }
        throw new ClientForbiddenException("Client is Forbidden!");
    }
}
