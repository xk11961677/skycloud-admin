package com.skycloud.common.client;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 *	@author sky
 */
@EnableFeignClients(basePackageClasses=ClientConfiguration.class)
@ComponentScan
@EnableCircuitBreaker
public class ClientConfiguration {
	/**
	 * 服务不可用的消息定义
	 */
	public static final String SERVICE_UNAVAILABLE = "service.unavailable";
	
}
