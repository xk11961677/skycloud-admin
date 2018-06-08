package com.skycloud.auth.server.service.impl;

import com.skycloud.auth.server.mapper.AuthClientServiceMapper;
import com.skycloud.auth.server.model.domain.AuthClient;
import com.skycloud.auth.server.model.domain.AuthClientService;
import com.skycloud.auth.server.service.AuthClientServiceService;
import com.skycloud.common.support.BaseService;
import com.skycloud.common.support.IService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * 描述：</b><br>
 * @author：系统生成
 * @version:1.0
 */
@Service
@Transactional
public class AuthClientServiceServiceImpl extends BaseService<AuthClientService> implements AuthClientServiceService {

	@Resource
	private AuthClientServiceMapper authClientServiceMapper;

	@Transactional(readOnly = true,rollbackFor = Exception.class)
	@Override
	public List<AuthClient> getAllowClientByServiceId(String serviceId) {
		List<AuthClient> list = authClientServiceMapper.getAllowClientByServiceId(Long.parseLong(serviceId));
		return list;
	}

}
