package com.skycloud.auth.client;



import com.skycloud.auth.client.configuration.AuthAutoConfigurer;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AuthAutoConfigurer.class)
@Documented
@Inherited
public @interface EnableAuthClient {
}
