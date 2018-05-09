package com.skycloud.base.oauth.server.service;


import com.skycloud.base.common.dto.UserDTO;

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
