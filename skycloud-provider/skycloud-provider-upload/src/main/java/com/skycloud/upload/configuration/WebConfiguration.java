package com.skycloud.upload.configuration;

import com.skycloud.auth.client.interceptor.ServiceAuthRestInterceptor;
import com.skycloud.auth.client.interceptor.UserAuthRestInterceptor;
import com.skycloud.core.handler.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author sky
 **/
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    GlobalExceptionHandler getGlobalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Bean
    public ServiceAuthRestInterceptor serviceAuthRestInterceptor() {
        return new ServiceAuthRestInterceptor();
    }

    @Bean
    public UserAuthRestInterceptor userAuthRestInterceptor() {
        return new UserAuthRestInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(serviceAuthRestInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(userAuthRestInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }


}
