package com.skycloud.auth.server.service.impl;

import com.skycloud.auth.common.utils.JwtUtil;
import com.skycloud.auth.server.service.AuthService;
import com.skycloud.user.dto.UserDto;
import org.springframework.stereotype.Service;

/**
 * @author sky
 **/
@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public String login(UserDto userDTO) {
        String token = JwtUtil.sign(userDTO, 3600000);
        return token;
    }

    @Override
    public boolean validate(String token) {
        UserDto userDTO = JwtUtil.unsign(token, UserDto.class);
        Boolean validate = userDTO==null?true:false;
        return validate;
    }

    @Override
    public String invalid(String token) {
        //TODO
        return null;
    }

    @Override
    public String refresh(String token) {
        return null;
    }
}
