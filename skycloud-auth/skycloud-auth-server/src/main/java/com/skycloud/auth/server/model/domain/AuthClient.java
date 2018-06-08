package com.skycloud.auth.server.model.domain;

import com.skycloud.common.mybatis.BaseEntity;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author sky
 * @date 2018-6-8
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "sc_auth_client")
public class AuthClient extends BaseEntity {
    /**
     * 服务编码
     */
    private String code;

    /**
     * 服务密钥
     */
    private String secret;

    /**
     * 服务名
     */
    private String name;

    /**
     * 是否锁定
     */
    private String locked;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建主机
     */
    @Column(name = "crt_host")
    private String crtHost;

    /**
     * 更新主机
     */
    @Column(name = "upd_host")
    private String updHost;

    private static final long serialVersionUID = 8889995097410914407L;
}