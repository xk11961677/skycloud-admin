package com.skycloud.user.model.domain;

import javax.persistence.*;

import com.skycloud.core.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户表
 * @author sky
 * @date 2018-6-8
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "sc_user")
public class User extends BaseEntity {
    /**
     * 昵称
     */
    private String name;

    /**
     * 用户编码
     */
    private String code;

    /**
     * 登录名称
     */
    private String mobile;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 状态
     */
    private Short status;

    @Column(name = "nick_name")
    private String nickName;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    private static final long serialVersionUID = 2535284092424927702L;
}