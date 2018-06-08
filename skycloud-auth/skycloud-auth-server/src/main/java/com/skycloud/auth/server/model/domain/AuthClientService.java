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
@Table(name = "sc_auth_client_service")
public class AuthClientService extends BaseEntity {
    @Column(name = "service_id")
    private String serviceId;

    @Column(name = "client_id")
    private String clientId;

    private String description;

    @Column(name = "crt_host")
    private String crtHost;

    private static final long serialVersionUID = 5832174148275554111L;
}