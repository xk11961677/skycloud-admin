package com.skycloud.auth.server.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@NoArgsConstructor
public class ClientConfiguration {

    @Value("${auth.client.token-header:}")
    private String clientTokenHeader;

    @Value("${auth.client.id:}")
    private String clientId;

    @Value("${auth.client.secret:}")
    private String secret;

    @Value("${auth.client.expire:}")
    private long expire;
}
