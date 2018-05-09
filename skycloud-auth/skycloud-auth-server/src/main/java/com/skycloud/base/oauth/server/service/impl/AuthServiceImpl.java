package com.skycloud.base.oauth.server.service.impl;

import com.skycloud.base.common.dto.UserDTO;
import com.skycloud.base.oauth.common.utils.JwtUtil;
import com.skycloud.base.oauth.server.service.AuthService;
import org.springframework.stereotype.Service;

/**
 * @author sky
 **/
@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public String login(UserDTO userDTO) {
        String token = JwtUtil.sign(userDTO, 3600);
        return token;
    }

    @Override
    public boolean validate(String token) {
        UserDTO userDTO = JwtUtil.unsign(token, UserDTO.class);
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
