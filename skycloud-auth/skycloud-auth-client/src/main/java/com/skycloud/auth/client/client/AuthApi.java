package com.skycloud.auth.client.client;

import com.skycloud.common.base.Result;
import com.skycloud.auth.client.annotation.IgnoreClientToken;
import com.skycloud.auth.common.dto.AuthClientDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@FeignClient(name = "${auth.service-id}",configuration= {}, fallback = AuthApi.AuthFeignFallback.class)
public interface AuthApi {

    /**
     * 获取access-token
     * @param clientId
     * @param secret
     * @return
     */
    @RequestMapping("/client/token")
    @IgnoreClientToken
    @ResponseBody Result<String> getAccessToken(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret);

    /**
     * 根据clientId获取所有可访问服务
     * @param clientId
     * @return
     */
    @RequestMapping("/client/myClient")
    @IgnoreClientToken
    @ResponseBody Result<List<AuthClientDTO>> getAllowClient(@RequestParam("clientId") String clientId);

    /**
     * 登录获取token
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/jwt/token")
    @ResponseBody Result<String> login(@RequestParam("username") String username ,@RequestParam("password") String password);

    /**
     * 验证登录获取的token是否有效
     * @return
     */
    @RequestMapping("/jwt/verify")
    @ResponseBody Result<Boolean> verify();




    @Component
    class AuthFeignFallback implements AuthApi {

        @Override
        public Result getAccessToken(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret) {
            return Result.getSuccessResult();
        }

        @Override
        public Result<List<AuthClientDTO>> getAllowClient(@RequestParam("clientId") String clientId) {
            return Result.getSuccessResult();
        }

        @Override
        public Result<String> login(@RequestParam("username") String username ,@RequestParam("password") String password) {
            return Result.getSuccessResult();
        }

        @Override
        public Result<Boolean> verify() {
            return Result.getSuccessResult();
        }
    }
}