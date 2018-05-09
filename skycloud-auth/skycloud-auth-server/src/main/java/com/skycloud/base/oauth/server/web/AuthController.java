package com.skycloud.base.oauth.server.web;

import com.skycloud.base.common.base.Result;
import com.skycloud.base.common.client.UserClient;
import com.skycloud.base.common.dto.UserDTO;
import com.skycloud.base.common.enumcode.FailureCodeEnum;
import com.skycloud.base.oauth.server.config.AuthProperties;
import com.skycloud.base.oauth.server.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author sky
 * @description 统一认证服务接口
 **/
@Controller
@RequestMapping("jwt")
@Slf4j
public class AuthController {

    @Autowired
    private UserClient userClient;

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthProperties authProperties;

    /**
     * 用户认证
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("token")
    @ResponseBody
    public Result<String> crtAuthenticationToken(String username , String password) {

        Result<UserDTO> result = userClient.getUser(username, password);

        UserDTO userDTO  = result.getData();

        if(userDTO == null ) {
            return Result.getFailureResult(FailureCodeEnum.SERVICE_EXCEPTION.getCode(),"账号或密码错误");
        }

        String token = authService.login(userDTO);

        return Result.getSuccessResult(token);
    }

    /**
     * 验证header中token是否有效
     * @return
     */
    @RequestMapping("verify")
    @ResponseBody
    public Result<Boolean> verify(HttpServletRequest request) {
        String tokenHeader = authProperties.getTokenHeader();

        String token = request.getHeader(tokenHeader);

        boolean validate = authService.validate(token);

        return Result.getSuccessResult(validate);
    }

    /**
     * 使用refresh_token刷新access_token
     * @param request
     * @return
     */
    @RequestMapping("refresh")
    @ResponseBody
    public Result<String> refreshAuthenticationToken(HttpServletRequest request) {
        String tokenHeader = authProperties.getTokenHeader();

        String token = request.getHeader(tokenHeader);

        String refresh = authService.refresh(token);

        if(refresh==null) {
            return Result.getFailureResult(FailureCodeEnum.SERVICE_EXCEPTION.getCode(),"token过期");
        }else {
            return Result.getSuccessResult(refresh);
        }
    }

    /**
     * 将token失效
     * @param request
     * @return
     */
    @RequestMapping("invlid")
    @ResponseBody
    public Result<Boolean> invlid(HttpServletRequest request) {
        String tokenHeader = authProperties.getTokenHeader();
        String token = request.getHeader(tokenHeader);
        authService.invalid(token);
        return Result.getSuccessResult(true);
    }

}
