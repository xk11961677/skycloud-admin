package com.skycloud.auth.client.client;

import com.skycloud.common.base.Result;
import com.skycloud.common.client.AbstractClientFallback;
import com.skycloud.auth.client.annotation.IgnoreClientToken;
import com.skycloud.auth.common.dto.AuthClientDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;


@FeignClient(name = "${auth.serviceId}",configuration  = {},fallback = AuthClient.AuthFeignFallback.class)
public interface AuthClient {

    /**
     * 获取access-token
     * @param clientId
     * @param secret
     * @return
     */
    @RequestMapping("/client/token")
    @ResponseBody
    @IgnoreClientToken
    Result<String> getAccessToken(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret);

    /**
     * 根据clientId获取所有可访问服务
     * @param clientId
     * @return
     */
    @RequestMapping("/client/myClient")
    @ResponseBody
    @IgnoreClientToken
    Result<List<AuthClientDTO>> getAllowClient(@RequestParam("clientId") String clientId);

    /**
     * 登录获取token
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/jwt/token")
    @ResponseBody
    @IgnoreClientToken
    Result<String> login(@RequestParam("username") String username ,@RequestParam("password") String password);

    /**
     * 验证登录获取的token是否有效
     * @return
     */
    @RequestMapping("/jwt/verify")
    @ResponseBody
    @IgnoreClientToken
    Result<Boolean> verify();




    @Component
    class AuthFeignFallback extends AbstractClientFallback implements AuthClient {

        @Override
        public Result getAccessToken(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret) {
            return new Result<>();
        }

        @Override
        public Result<List<AuthClientDTO>> getAllowClient(@RequestParam("clientId") String clientId) {
            return new Result<>();
        }

        @Override
        public Result<String> login(@RequestParam("username") String username ,@RequestParam("password") String password) {
            return new Result<>();
        }

        @Override
        public Result<Boolean> verify() {
            return new Result<>();
        }
    }
}
