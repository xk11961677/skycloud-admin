package com.skycloud.auth.server.web;

import com.skycloud.api.client.user.UserApi;
import com.skycloud.api.dto.UserDTO;
import com.skycloud.auth.server.configuration.UserAuthConfiguration;
import com.skycloud.common.base.Result;
import com.skycloud.common.enumcode.FailureCodeEnum;
import com.skycloud.auth.server.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author sky
 * @description 统一认证服务接口
 **/
@Controller
@RequestMapping("jwt")
@Slf4j
public class AuthController {

    @Resource
    private UserApi userApi;

    @Resource
    private AuthService authService;

    @Resource
    private UserAuthConfiguration userAuthConfiguration;

    /**
     * 用户认证
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("token")
    @ResponseBody
    public Result<String> crtAuthenticationToken(String username , String password) {

        Result<UserDTO> result = userApi.getUser(username, password);

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
        String tokenHeader = userAuthConfiguration.getUserTokenHeader();

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
        String tokenHeader = userAuthConfiguration.getUserTokenHeader();

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
        String tokenHeader = userAuthConfiguration.getUserTokenHeader();
        String token = request.getHeader(tokenHeader);
        authService.invalid(token);
        return Result.getSuccessResult(true);
    }

}
