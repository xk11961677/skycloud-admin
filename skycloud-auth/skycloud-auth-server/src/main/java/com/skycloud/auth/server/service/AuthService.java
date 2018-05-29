package com.skycloud.auth.server.service;


import com.skycloud.api.dto.UserDTO;

/**
 * @author sky
 **/
public interface AuthService {

    /**
     * 生成登录后的TOKEN
     * @param userDTO
     * @return
     */
    String login(UserDTO userDTO);

    /**
     * 验证token
     * @return
     */
    boolean validate(String token);

    /**
     * 失效token
     * @param token
     * @return
     */
    String invalid(String token);

    /**
     * 刷新token
     * @param token
     * @return
     */
    String refresh(String token);
}
