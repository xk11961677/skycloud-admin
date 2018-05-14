package com.skycloud.auth.server.configuration;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@NoArgsConstructor
public class UserAuthConfiguration {

    @Value("${auth.user.token-header:}")
    private String userTokenHeader;

    @Value("${auth.user.expire:}")
    private long expire;

}