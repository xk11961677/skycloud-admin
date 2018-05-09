package com.skycloud.base.oauth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 应用入口
 *
 * @author sky
 */

@SpringCloudApplication
@EnableEurekaClient
public class AuthApplication {


    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 入口
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
