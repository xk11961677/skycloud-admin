package com.skycloud.user;

import com.skycloud.auth.client.EnableAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author sky
 */

@SpringCloudApplication
@EnableEurekaClient
@EnableAuthClient
public class UserApplication {

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
        SpringApplication.run(UserApplication.class, args);
    }

}