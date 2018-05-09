//package com.skycloud.base.user.config;
//
//import com.skycloud.base.oauth.client.interceptor.ClientTokenInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
///**
// * @author sky
// **/
//@Configuration
//public class MyWebConfig extends WebMvcConfigurerAdapter {
//
//   @Bean
//   public ClientTokenInterceptor clientTokenInterceptor() {
//       return new ClientTokenInterceptor();
//   }
//
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(clientTokenInterceptor()).addPathPatterns("/**");
//        super.addInterceptors(registry);
//    }
//}
