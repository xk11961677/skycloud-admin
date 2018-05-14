package com.skycloud.upload;

import com.skycloud.auth.client.EnableAuthClient;
import com.skycloud.upload.configuration.OssConfiguration;
import com.skycloud.upload.configuration.UploadConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import javax.servlet.MultipartConfigElement;

/**
 * 应用入口
 *
 * @author lws
 */

@SpringCloudApplication
@EnableEurekaClient
@EnableAuthClient
public class UploadApplication {


    /**
     * 构造
     */
    public UploadApplication() {
        // do nothing
    }

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
        SpringApplication.run(UploadApplication.class, args);
    }


    /**
     * 配置
     *
     * @return
     */
    @Bean
    public UploadConfiguration uploadConfiguration() {
        return new UploadConfiguration();
    }

    @Bean
    public OssConfiguration ossConfiguration() {
       return new OssConfiguration();
    }

    /**
     * 上传配置(最大10M)
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement(@Qualifier("uploadConfiguration") UploadConfiguration cfg) {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(cfg.getMaxFileSize() * 1024);
        factory.setMaxRequestSize(cfg.getMaxFileSize() * 1024);
        return factory.createMultipartConfig();
    }
}
