package com.skycloud.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author sky
 **/
@Data
public class UserDTO implements Serializable {

    /**
     *
     */
    private Integer id;
    /**
     *昵称
     */
    private String userName;
    /**
     *用户编码
     */
    private String userCode;
    /**
     *登录名称
     */
    private String password;
    /**
     *登录密码
     */
    private String mobile;
    /**
     *真实姓名
     */
    private String realName;
    /**
     *状态
     */
    private Integer status;

}
