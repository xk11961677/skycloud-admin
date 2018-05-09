package com.skycloud.auth.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AuthClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Integer id;
    /**
     *服务编码
     */
    private String code;
    /**
     *服务密钥
     */
    private String secret;
    /**
     *服务名
     */
    private String name;
    /**
     *是否锁定
     */
    private String locked;
    /**
     *描述
     */
    private String description;
    /**
     *创建时间
     */
    private Date crtTime;
    /**
     *创建人
     */
    private String crtUser;
    /**
     *创建人姓名
     */
    private String crtName;
    /**
     *创建主机
     */
    private String crtHost;
    /**
     *更新时间
     */
    private Date updTime;
    /**
     *更新人
     */
    private String updUser;
    /**
     *更新姓名
     */
    private String updName;
    /**
     *更新主机
     */
    private String updHost;
    /**
     *
     */
    private String attr1;
    /**
     *
     */
    private String attr2;
    /**
     *
     */
    private String attr3;
    /**
     *
     */
    private String attr4;
    /**
     *
     */
    private String attr5;
    /**
     *
     */
    private String attr6;
    /**
     *
     */
    private String attr7;
    /**
     *
     */
    private String attr8;
}
