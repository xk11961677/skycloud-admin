package com.skycloud.auth.server.service;

import com.skycloud.auth.server.common.service.BaseService;
import com.skycloud.auth.server.entity.AuthClientEntity;

/**
 * 描述：</b><br>
 * @author：系统生成
 * @version:1.0
 */
public interface AuthClientService extends BaseService {
	
	/**
     * 描述: 根据主键查询
	 * @param id  
     * @return
     */
	public AuthClientEntity get(Integer id);
	
	
	/*user customize code start*/

	/*user customize code end*/
}
