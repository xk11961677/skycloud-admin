package com.skycloud.upload.web;

import com.skycloud.api.client.user.UserApi;
import com.skycloud.api.dto.UserDTO;
import com.skycloud.common.base.BaseContextHandler;
import com.skycloud.common.base.Result;
import com.skycloud.upload.configuration.OssConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author sky
 **/
@Controller
@RequestMapping("authTest")
@Slf4j
public class AuthTestController {

    @Resource
    private UserApi userApi;

    /**
     * 服务之间鉴权
     * @return
     */
    @RequestMapping("authTest")
    @ResponseBody
    public Result<UserDTO> authTest() {
        String token = BaseContextHandler.getToken();
        log.info("login success "+token);
        Result<UserDTO> user = userApi.getUser("admin", "admin");
        return user;
    }
}
