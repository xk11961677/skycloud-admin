package com.skycloud.upload.property;

import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

/**
 * 自定义加载外部属性
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ CustomPropertyRegistrar.class })
public @interface CustomPropeprty {
	/**
	 * 属性文件名称
	 * @return
	 */
	String value();

}
