package com.skycloud.auth.server.configuration;

import com.skycloud.auth.server.interceptor.ServiceAuthRestInterceptor;
import com.skycloud.auth.server.interceptor.UserAuthRestInterceptor;
import com.skycloud.common.handler.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 */
@Configuration
@Primary
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    GlobalExceptionHandler getGlobalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getServiceAuthRestInterceptor()).addPathPatterns("/service/**");
        registry.addInterceptor(getUserTokenInterceptor()).addPathPatterns("/service/**");
    }

    @Bean
    ServiceAuthRestInterceptor getServiceAuthRestInterceptor() {
        return new ServiceAuthRestInterceptor();
    }

    @Bean
    UserAuthRestInterceptor getUserTokenInterceptor() {
        return new UserAuthRestInterceptor();
    }

}
