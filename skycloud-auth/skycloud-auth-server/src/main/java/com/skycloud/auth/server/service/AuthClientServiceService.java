package com.skycloud.auth.server.service;

import com.skycloud.auth.server.model.domain.AuthClient;
import com.skycloud.auth.server.model.domain.AuthClientService;
import com.skycloud.core.support.IService;

import java.util.List;

/**
 * 描述：</b><br>
 * @author：系统生成
 * @version:1.0
 */
public interface AuthClientServiceService extends IService<AuthClientService> {
	
	/**
	 * 获取某个服务可访问的目标服务
	 * @param serviceId
	 * @return
	 */
	List<AuthClient> getAllowClientByServiceId(String serviceId);

}
