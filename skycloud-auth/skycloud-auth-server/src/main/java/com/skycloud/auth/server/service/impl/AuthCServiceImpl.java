package com.skycloud.auth.server.service.impl;

import com.skycloud.auth.server.model.domain.AuthClient;
import com.skycloud.common.exception.auth.ClientInvalidException;
import com.skycloud.auth.server.service.AuthCService;
import com.skycloud.auth.server.service.AuthClientService;
import com.skycloud.auth.server.service.AuthClientServiceService;
import com.skycloud.auth.common.dto.AuthJwtDTO;
import com.skycloud.auth.common.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author sky
 **/
@Service
@Transactional
public class AuthCServiceImpl implements AuthCService {

    @Autowired
    private AuthClientService authClientService;


    @Autowired
    private AuthClientServiceService authClientServiceService;

    /**
     * 根据clientId与secret获取access_token
     * @param clientId
     * @param secret
     * @return
     */
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    @Override
    public String apply(String clientId, String secret)  {
        AuthClient client = getClient(clientId, secret);
        AuthJwtDTO authJwtDTO = new AuthJwtDTO();
        authJwtDTO.setCode(client.getCode());
        authJwtDTO.setName(client.getName());
        authJwtDTO.setId(client.getId());
        String token = JwtUtil.sign(authJwtDTO, 3600000);
        return token;
    }

    /**
     *
     * @param clientId
     * @param secret
     * @throws Exception
     */
    @Override
    public void validate(String clientId, String secret) throws Exception {
        AuthClient client = new AuthClient();
        client.setCode(clientId);
        client = authClientService.selectOne(client);

        if(client != null && client.getSecret().equals(secret)){
            throw new ClientInvalidException("Client not found or Client secret is error!");
        }
    }

    @Override
    public List<String> getAllowedClient(String serviceId, String secret) {
        return null;
    }

    /**
     * 根据serviceId获取client实体
     * @param serviceId
     * @return
     */
    @Override
    public List<AuthClient> getAllowedClientByServiceId(String serviceId) {
        List<AuthClient> allowClient = authClientServiceService.getAllowClientByServiceId(serviceId);
        return allowClient;
    }

    @Override
    public void registryClient() {

    }

    /**
     * 根据clientId与secret获取所有client
     * @param clientId
     * @param secret
     * @return
     */
    private AuthClient getClient(String clientId,String secret) {
        AuthClient client = new AuthClient();
        client.setCode(clientId);
        client = authClientService.selectOne(client);

        if(client != null && client.getSecret().equals(secret)){
            throw new ClientInvalidException("Client not found or Client secret is error!");
        }
        return client;
    }

}
