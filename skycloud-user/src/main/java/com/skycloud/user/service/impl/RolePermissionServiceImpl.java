package com.skycloud.user.service.impl;

import com.skycloud.user.common.service.impl.BaseServiceImpl;
import com.skycloud.user.entity.RolePermissionEntity;
import com.skycloud.user.service.RolePermissionService;
import org.springframework.stereotype.Repository;
import lombok.extern.slf4j.Slf4j;

 /**
 * 描述：</b><br>
 * @author：系统生成
 * @version:1.0
 */
@Repository
@Slf4j
public class RolePermissionServiceImpl extends BaseServiceImpl implements RolePermissionService {

	
	@Override
	public RolePermissionEntity get(Integer id){
		RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
		rolePermissionEntity.setId(id);
		return (RolePermissionEntity)get(rolePermissionEntity);
	}
	
	
	/*user customize code start*/

	/*user customize code end*/
}
