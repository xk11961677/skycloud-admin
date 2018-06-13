package com.skycloud.user.web.rpc;

import com.skycloud.auth.client.annotation.IgnoreUserToken;
import com.skycloud.base.BaseContextHandler;
import com.skycloud.base.ResponseVo;
import com.skycloud.user.dto.UserDto;
import com.skycloud.user.model.domain.User;
import com.skycloud.user.service.UserRestApi;
import com.skycloud.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author sky
 * @description
 **/
@RestController
@RequestMapping("/user")
@Slf4j
@IgnoreUserToken
public class UserController implements UserRestApi {

    @Resource
    private UserService userService;

    /**
     * 根据用户名和密码获取用户
     *
     * @return
     */
    @RequestMapping("/getUser")
    @ResponseBody
    @Override
    public ResponseVo<UserDto> getUser(@RequestBody UserDto userDto) {
        String token = BaseContextHandler.getToken();
        log.info("login success "+token);
        ResponseVo<UserDto> result;
        UserDto userDTO = null;
        User user = new User();
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        User userVo = userService.selectOne(user);
        if (userVo != null) {
            userDTO = new UserDto();
            BeanUtils.copyProperties(userVo, userDTO);
        }
        result = ResponseVo.ok(userDTO);
        return result;
    }

}
