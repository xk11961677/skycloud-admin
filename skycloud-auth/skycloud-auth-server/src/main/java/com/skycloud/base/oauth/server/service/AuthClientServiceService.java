package com.skycloud.base.oauth.server.service;

import com.skycloud.base.oauth.server.common.service.BaseService;
import com.skycloud.base.oauth.server.entity.AuthClientEntity;
import com.skycloud.base.oauth.server.entity.AuthClientServiceEntity;

import java.util.List;

/**
 * 描述：</b><br>
 * @author：系统生成
 * @version:1.0
 */
public interface AuthClientServiceService extends BaseService {
	
	/**
     * 描述: 根据主键查询
	 * @param id  
     * @return
     */
	public AuthClientServiceEntity get(Integer id);
	
	
	/*user customize code start*/

	/**
	 * 获取某个服务可访问的目标服务
	 * @param serviceId
	 * @return
	 */
	List<AuthClientEntity> getAllowClient(String serviceId);

	/*user customize code end*/
}
