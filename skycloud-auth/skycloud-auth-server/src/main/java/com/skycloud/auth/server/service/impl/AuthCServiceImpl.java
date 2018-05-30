package com.skycloud.auth.server.service.impl;

import com.skycloud.api.dto.UserDTO;
import com.skycloud.auth.server.common.exception.ClientInvalidException;
import com.skycloud.auth.server.entity.AuthClientEntity;
import com.skycloud.auth.server.service.AuthCService;
import com.skycloud.auth.server.service.AuthClientService;
import com.skycloud.auth.server.service.AuthClientServiceService;
import com.skycloud.auth.common.dto.AuthJwtDTO;
import com.skycloud.auth.common.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sky
 **/
@Service
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
     * @throws Exception
     */
    @Override
    public String apply(String clientId, String secret) throws Exception {
        AuthClientEntity client = getClient(clientId, secret);
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
        AuthClientEntity client = new AuthClientEntity();
        client.setCode(clientId);
        List<AuthClientEntity> list = (List<AuthClientEntity>)authClientService.getList(client);

        if(list.size()==0||!list.get(0).getSecret().equals(secret)){
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
    public List<AuthClientEntity> getAllowedClient(String serviceId) {
        List<AuthClientEntity> allowClient = authClientServiceService.getAllowClient(serviceId);
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
    private AuthClientEntity getClient(String clientId,String secret) {
        AuthClientEntity client = new AuthClientEntity();
        client.setCode(clientId);
        List<AuthClientEntity> list = (List<AuthClientEntity>)authClientService.getList(client);

        if(list.size()==0 || !list.get(0).getSecret().equals(secret)){
            throw new ClientInvalidException("Client not found or Client secret is error!");
        }
        client = list.get(0);
        return client;
    }

}
