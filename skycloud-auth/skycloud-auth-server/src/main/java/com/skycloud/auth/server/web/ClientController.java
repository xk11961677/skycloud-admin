package com.skycloud.auth.server.web;

import com.skycloud.common.base.Result;
import com.skycloud.auth.server.entity.AuthClientEntity;
import com.skycloud.auth.server.service.AuthCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author sky
 * @description 服务之间鉴权
 **/
@Controller
@RequestMapping("client")
public class ClientController {

    @Autowired
    private AuthCService authCService;

    /**
     * 获取访问token
     * @param clientId
     * @param secret
     * @return
     */
    @RequestMapping("token")
    @ResponseBody
    public Result<String> getAccessToken(String clientId, String secret) throws Exception{
        return Result.getSuccessResult(authCService.apply(clientId,secret));
    }

    /**
     * 获取我能访问的所有服务
     * @param clientId
     * @return
     */
    @RequestMapping("myClient")
    @ResponseBody
    public Result<List<AuthClientEntity>> getAllowedClient(String clientId) {
        List<AuthClientEntity> rsList = authCService.getAllowedClient(clientId);
        return Result.getSuccessResult(rsList);
    }

    /*@RequestMapping(value = "/servicePubKey",method = RequestMethod.POST)
    public ObjectRestResponse<byte[]> getServicePublicKey(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret) throws Exception {
        authClientService.validate(clientId, secret);
        return new ObjectRestResponse<byte[]>().data(keyConfiguration.getServicePubKey());
    }

    @RequestMapping(value = "/userPubKey",method = RequestMethod.POST)
    public ObjectRestResponse<byte[]> getUserPublicKey(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret) throws Exception {
        authClientService.validate(clientId, secret);
        return new ObjectRestResponse<byte[]>().data(keyConfiguration.getUserPubKey());
    }*/

}
