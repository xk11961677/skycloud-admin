package com.skycloud.geteway.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.skycloud.api.dto.UserDTO;
import com.skycloud.auth.client.client.AuthClient;
import com.skycloud.auth.client.configuration.ClientConfiguration;
import com.skycloud.auth.client.configuration.UserAuthConfiguration;
import com.skycloud.auth.client.util.ServiceAuthUtil;
import com.skycloud.auth.common.utils.JwtUtil;
import com.skycloud.common.base.BaseContextHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 *
 */
@Component
@Slf4j
public class AdminAccessFilter extends ZuulFilter {

    @Resource
    @Lazy
    private ServiceAuthUtil serviceAuthUtil;

    @Resource
    @Lazy
    private UserAuthConfiguration userAuthConfiguration;

    @Resource
    @Lazy
    private ClientConfiguration clientConfiguration;

    @Value("${gate.ignore.startWith}")
    private String startWith;

    @Value("${zuul.prefix}")
    private String zuulPrefix;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        final String requestUri = request.getRequestURI().substring(zuulPrefix.length());
        final String method = request.getMethod();
        BaseContextHandler.setToken(null);
        // 不进行拦截的地址
        if (isStartWith(requestUri)) {
            return null;
        }
        UserDTO user = null;
        try {
            user = getJWTUser(request, ctx);
        } catch (Exception e) {
            setFailedRequest("请登录后操作", 200);
            return null;
        }
        //TODO 判断用户是否由此资源操作权限

        // 申请客户端密钥头
        String accessToken = serviceAuthUtil.acquireToken();
        ctx.addZuulRequestHeader(clientConfiguration.getClientTokenHeader(), accessToken);
        return null;
    }

    /**
     * 返回session中的用户信息
     *
     * @param request
     * @param ctx
     * @return
     */
    private UserDTO getJWTUser(HttpServletRequest request, RequestContext ctx) throws Exception {
        String authToken = request.getHeader(userAuthConfiguration.getUserTokenHeader());
        if (StringUtils.isBlank(authToken)) {
            authToken = request.getParameter("token");
        }
        ctx.addZuulRequestHeader(userAuthConfiguration.getUserTokenHeader(), authToken);
        BaseContextHandler.setToken(authToken);
        return JwtUtil.unsign(authToken, UserDTO.class);
    }

    /**
     * URI是否以什么打头
     *
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        for (String s : startWith.split(",")) {
            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return flag;
    }

    /**
     * 网关抛异常
     *
     * @param body
     * @param code
     */
    private void setFailedRequest(String body, int code) {
        log.debug("Reporting error ({}): {}", code, body);
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody(body);
            ctx.setSendZuulResponse(false);
        }
    }

}
