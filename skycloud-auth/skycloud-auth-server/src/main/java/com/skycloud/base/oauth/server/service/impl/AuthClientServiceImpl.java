package com.skycloud.base.oauth.server.service.impl;

import com.skycloud.base.oauth.server.common.service.impl.BaseServiceImpl;
import com.skycloud.base.oauth.server.entity.AuthClientEntity;
import com.skycloud.base.oauth.server.service.AuthClientService;
import org.springframework.stereotype.Repository;
import lombok.extern.slf4j.Slf4j;

 /**
 * 描述：</b><br>
 * @author：系统生成
 * @version:1.0
 */
@Repository
@Slf4j
public class AuthClientServiceImpl extends BaseServiceImpl implements AuthClientService {

	
	@Override
	public AuthClientEntity get(Integer id){
		AuthClientEntity authClientEntity = new AuthClientEntity();
		authClientEntity.setId(id);
		return (AuthClientEntity)get(authClientEntity);
	}
	
	
	/*user customize code start*/

	/*user customize code end*/
}
