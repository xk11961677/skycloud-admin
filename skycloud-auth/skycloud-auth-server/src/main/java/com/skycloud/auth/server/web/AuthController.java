package com.skycloud.auth.server.web;

import com.skycloud.user.dto.UserDto;
import com.skycloud.user.service.UserRestApi;
import com.skycloud.auth.server.configuration.UserAuthConfiguration;
import com.skycloud.base.ResponseData;
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
    private UserRestApi userApi;

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
    public ResponseData<String> crtAuthenticationToken(String username , String password) {

        UserDto userDto = new UserDto();
        userDto.setName(username);
        userDto.setPassword(password);
        ResponseData<UserDto> result = userApi.getUser(userDto);

        UserDto userDTO  = result.getData();

        if(userDTO == null ) {
            return ResponseData.error("","账号或密码错误");
        }

        String token = authService.login(userDTO);

        return ResponseData.ok(token);
    }

    /**
     * 验证header中token是否有效
     * @return
     */
    @RequestMapping("verify")
    @ResponseBody
    public ResponseData<Boolean> verify(HttpServletRequest request) {
        String tokenHeader = userAuthConfiguration.getUserTokenHeader();

        String token = request.getHeader(tokenHeader);

        boolean validate = authService.validate(token);

        return ResponseData.ok(validate);
    }

    /**
     * 使用refresh_token刷新access_token
     * @param request
     * @return
     */
    @RequestMapping("refresh")
    @ResponseBody
    public ResponseData<String> refreshAuthenticationToken(HttpServletRequest request) {
        String tokenHeader = userAuthConfiguration.getUserTokenHeader();

        String token = request.getHeader(tokenHeader);

        String refresh = authService.refresh(token);

        if(refresh==null) {
            return ResponseData.error("","token过期");
        }else {
            return ResponseData.ok(refresh);
        }
    }

    /**
     * 将token失效
     * @param request
     * @return
     */
    @RequestMapping("invlid")
    @ResponseBody
    public ResponseData<Boolean> invlid(HttpServletRequest request) {
        String tokenHeader = userAuthConfiguration.getUserTokenHeader();
        String token = request.getHeader(tokenHeader);
        authService.invalid(token);
        return ResponseData.ok(true);
    }

}
