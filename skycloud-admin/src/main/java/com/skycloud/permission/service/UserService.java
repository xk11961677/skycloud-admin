package com.skycloud.permission.service;

import com.skycloud.permission.common.service.BaseService;
import com.skycloud.permission.entity.UserEntity;

/**
 * 描述：</b><br>
 * @author：系统生成
 * @version:1.0
 */
public interface UserService extends BaseService {
	
	/**
     * 描述: 根据主键查询
	 * @param id  
     * @return
     */
	public UserEntity get(Integer id);
	
	
	/*user customize code start*/

	/*user customize code end*/
}
