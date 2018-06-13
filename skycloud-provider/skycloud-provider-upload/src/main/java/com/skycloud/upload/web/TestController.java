package com.skycloud.upload.web;

import com.skycloud.base.BaseContextHandler;
import com.skycloud.base.ResponseVo;
import com.skycloud.user.dto.UserDto;
import com.skycloud.user.service.UserRestApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author sky
 **/
@Controller
@RequestMapping("test")
@Slf4j
public class TestController {

    @Resource
    private UserRestApi userRestApi;

    /**
     * 服务之间鉴权
     * @return
     */
    @RequestMapping("authTest")
    @ResponseBody
    public ResponseVo<UserDto> authTest() {
        String token = BaseContextHandler.getToken();
        log.info("login success "+token);
        UserDto userDto = new UserDto();
        userDto.setName("admin");
        userDto.setPassword("admin");
        ResponseVo<UserDto> user = userRestApi.getUser(userDto);
        return user;
    }
}
