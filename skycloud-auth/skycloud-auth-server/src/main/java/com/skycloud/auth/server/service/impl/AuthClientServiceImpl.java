package com.skycloud.auth.server.service.impl;

import com.skycloud.auth.server.model.domain.AuthClient;
import com.skycloud.auth.server.service.AuthClientService;
import com.skycloud.common.support.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Service
@Transactional
public class AuthClientServiceImpl extends BaseService<AuthClient> implements AuthClientService {

}
