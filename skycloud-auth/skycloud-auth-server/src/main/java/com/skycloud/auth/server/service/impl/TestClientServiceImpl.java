package com.skycloud.auth.server.service.impl;

import com.skycloud.auth.server.mapper.AuthClientMapper;
import com.skycloud.auth.server.model.domain.AuthClient;
import com.skycloud.auth.server.service.TestClientService;
import com.skycloud.common.base.ResponseData;
import com.skycloud.common.support.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class TestClientServiceImpl extends BaseService<AuthClient> implements TestClientService {

    @Resource
    private AuthClientMapper authClientMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AuthClient saveClient() {
        AuthClient client = new AuthClient();
        client.setCode("123");
        client.setName("456");
        int i = authClientMapper.insertUseGeneratedKeys(client);
        logger.info("===============>>:{}"+i);
        return client;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AuthClient modifyClientById(Long id) {
        AuthClient client = new AuthClient();
        client.setId(id);
        client.setCode("123");
        client.setName("456789");
        int i = authClientMapper.updateByPrimaryKeySelective(client);
        logger.info("===============>>:{}"+i);
        return client;
    }

}
