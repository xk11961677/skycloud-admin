package com.skycloud.user.web;

import com.skycloud.base.common.base.Result;
import com.skycloud.base.common.dto.UserDTO;
import com.skycloud.user.entity.UserEntity;
import com.skycloud.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sky
 * @description
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 根据用户名和密码获取用户
     *
     * @param loginName
     * @param password
     * @return
     */
    @RequestMapping("getUser")
    @ResponseBody
    public Result<UserDTO> getUser(String loginName, String password) {
        Result<UserDTO> result;
        UserDTO userDTO = null;
        UserEntity userEntity = new UserEntity();
        userEntity.setLoginName(loginName);
        userEntity.setLoginPassword(password);
        UserEntity user = (UserEntity) userService.get(userEntity);
        if (user != null) {
            userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
        }
        result = Result.getSuccessResult(userDTO);
        return result;
    }

}
