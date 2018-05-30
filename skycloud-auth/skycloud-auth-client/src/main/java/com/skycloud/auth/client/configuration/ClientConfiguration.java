package com.skycloud.auth.client.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Data
@NoArgsConstructor
public class ClientConfiguration {

    @Value("${auth.client.token-header:null}")
    private String clientTokenHeader;

    @Value("${auth.client.id:null}")
    private String clientId;

    @Value("${auth.client.secret:null}")
    private String secret;

    @Value("${auth.client.expire:null}")
    private long expire;
}
