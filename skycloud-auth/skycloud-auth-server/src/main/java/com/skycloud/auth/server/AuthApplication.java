package com.skycloud.auth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.web.client.RestTemplate;

/**
 * 应用入口
 *
 * @author sky
 */

@EnableHystrix
@EnableFeignClients(value = {"com.skycloud"})
@EnableEurekaClient
@SpringBootApplication
@ComponentScan(value = {"com.skycloud"})
public class AuthApplication {



    /**
     * 入口
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
