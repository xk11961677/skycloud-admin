package com.skycloud.geteway;

import com.skycloud.auth.client.EnableAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.web.client.RestTemplate;

/**
 * 应用入口
 *
 * @author sky
 */

@SpringCloudApplication
@EnableZuulProxy
@EnableAuthClient
@ComponentScan(value = "com.skycloud")
public class GetewayApplication {

    /**
     * 入口
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(GetewayApplication.class, args);
    }


}
