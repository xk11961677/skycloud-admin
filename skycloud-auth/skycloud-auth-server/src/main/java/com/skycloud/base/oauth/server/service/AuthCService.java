package com.skycloud.base.oauth.server.service;


import com.skycloud.base.oauth.server.entity.AuthClientEntity;

import java.util.List;

/**
 * @author sky
 **/
public interface AuthCService {

    /**
     *
     * @param clientId
     * @param secret
     * @return
     * @throws Exception
     */
    String apply(String clientId, String secret) throws Exception;

    /**
     *  验证客户端
     *  @param clientId
     *  @param secret
     *  @throws Exception
     */
    void validate(String clientId, String secret) throws Exception;

    /**
     *  获取授权的客户端列表
     *  @param serviceId
     *  @param secret
     *  @return
     */
    List<String> getAllowedClient(String serviceId, String secret);

    /**
     *  获取服务授权的客户端列表
     *  @param serviceId
     *  @return
     */
    List<AuthClientEntity> getAllowedClient(String serviceId);

    /**
     *
     */
    void registryClient();

}
