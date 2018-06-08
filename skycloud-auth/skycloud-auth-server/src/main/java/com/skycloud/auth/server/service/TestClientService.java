package com.skycloud.auth.server.service;

import com.skycloud.auth.server.model.domain.AuthClient;
import com.skycloud.core.support.IService;

public interface TestClientService extends IService<AuthClient> {

    AuthClient saveClient();

    AuthClient modifyClientById(Long id);

}
