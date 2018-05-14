package com.skycloud.auth.server.service.impl;

import com.skycloud.auth.server.common.service.impl.BaseServiceImpl;
import com.skycloud.auth.server.entity.AuthClientEntity;
import com.skycloud.auth.server.entity.AuthClientServiceEntity;
import com.skycloud.auth.server.service.AuthClientServiceService;
import org.springframework.stereotype.Repository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


/**
 * 描述：</b><br>
 * @author：系统生成
 * @version:1.0
 */
@Repository
@Slf4j
public class AuthClientServiceServiceImpl extends BaseServiceImpl implements AuthClientServiceService {

	
	@Override
	public AuthClientServiceEntity get(Integer id){
		AuthClientServiceEntity authClientServiceEntity = new AuthClientServiceEntity();
		authClientServiceEntity.setId(id);
		return (AuthClientServiceEntity)get(authClientServiceEntity);
	}

	@Override
	public List<AuthClientEntity> getAllowClient(String serviceId) {
		return getList("getAllowClient",serviceId);
	}
	
	
	/*user customize code start*/

	/*user customize code end*/
}
