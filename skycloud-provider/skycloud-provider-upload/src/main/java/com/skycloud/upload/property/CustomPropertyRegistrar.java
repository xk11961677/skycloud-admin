package com.skycloud.upload.property;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.Properties;

/**
 * @author sky
 * 通用外部属性加载. <br>
 */
@Log4j
public class CustomPropertyRegistrar implements ImportBeanDefinitionRegistrar {

	/**
	 * 将属性文件填充容器
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		Map<String, Object> map = importingClassMetadata.getAnnotationAttributes(CustomPropeprty.class.getName(),
				false);
		String value = (String) map.get("value");
		Environment env = ((BeanFactory) registry).getBean(Environment.class);
		String url = getConfigUrl(env, value);
		if(url!=null) {
			Properties props = getConfigProperties(url,value);
			PropertyContainer.map.put(value,props);
		}
	}

	/**
	 * 从配置文件中查找属性的配置
	 * 
	 * @param url
	 * @return
	 */
	private Properties getConfigProperties(String url,String val) {
		String rs = new RestTemplate().getForObject(url, String.class);
		Properties prop = new Properties();
		if(rs!=null && !"".equals(rs)) {
			String[] value = rs.split("\\r?\\n");;
			try {
				for(int i=0; i<value.length; i++) {
					String key = value[i].split(":")[0].trim();
					if(key.contains(val)) {
						prop.put(key,value[i].split(":")[1].trim());
					}
				}
				return prop;
			} catch (RuntimeException e) {
				throw new IllegalArgumentException(e);
			}
		}
		return null;
	}

	/**
	 * 获得配置服务器上的配置文件路径
	 * 
	 * @param env
	 * @param name
	 * @return
	 */
	private String getConfigUrl(Environment env, String name) {
		String uri = env.getProperty("spring.cloud.config.uri");
		if (uri == null) {
			log.warn("Boot config 'spring.cloud.config.uri' is required ");
			return null;
		}
		String profile = env.getProperty("spring.cloud.config.profile");
		if (profile == null) {
			profile = "default";
		}
		return uri + "/" + name + "-" + profile+".properties";
	}
}
