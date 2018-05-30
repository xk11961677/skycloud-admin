package com.skycloud.upload.configuration;

import com.skycloud.auth.client.interceptor.ClientTokenInterceptor;
import com.skycloud.auth.client.interceptor.UserTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author sky
 **/
@Configuration
public class MyWebConfiguration extends WebMvcConfigurerAdapter {

   @Bean
   public ClientTokenInterceptor clientTokenInterceptor() {
       return new ClientTokenInterceptor();
   }

    @Bean
    public UserTokenInterceptor userTokenInterceptor() {
        return new UserTokenInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(clientTokenInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(userTokenInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }




}
