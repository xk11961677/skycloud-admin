package com.skycloud.user.service.impl;

import com.skycloud.user.common.service.impl.BaseServiceImpl;
import com.skycloud.user.entity.UserRoleEntity;
import org.springframework.stereotype.Repository;
import com.skycloud.user.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;

 /**
 * 描述：</b><br>
 * @author：系统生成
 * @version:1.0
 */
@Repository
@Slf4j
public class UserRoleServiceImpl extends BaseServiceImpl implements UserRoleService {

	
	@Override
	public UserRoleEntity get(Integer id){
		UserRoleEntity userRoleEntity = new UserRoleEntity();
		userRoleEntity.setId(id);
		return (UserRoleEntity)get(userRoleEntity);
	}
	
	
	/*user customize code start*/

	/*user customize code end*/
}
