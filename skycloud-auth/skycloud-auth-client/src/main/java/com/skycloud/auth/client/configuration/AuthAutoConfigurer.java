package com.skycloud.auth.client.configuration;


import com.skycloud.auth.client.EnableAuthClient;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = EnableAuthClient.class)
@EnableFeignClients(basePackageClasses = EnableAuthClient.class)
@EnableCircuitBreaker
public class AuthAutoConfigurer {

}
