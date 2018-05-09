package com.skycloud.user.service.impl;

import com.skycloud.user.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Repository;
import com.skycloud.user.service.PermissionService;
import com.skycloud.user.entity.PermissionEntity;
import lombok.extern.slf4j.Slf4j;

 /**
 * 描述：</b><br>
 * @author：系统生成
 * @version:1.0
 */
@Repository
@Slf4j
public class PermissionServiceImpl extends BaseServiceImpl implements PermissionService {

	
	@Override
	public PermissionEntity get(Integer id){
		PermissionEntity permissionEntity = new PermissionEntity();
		permissionEntity.setId(id);
		return (PermissionEntity)get(permissionEntity);
	}
	
	
	/*user customize code start*/

	/*user customize code end*/
}
