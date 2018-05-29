package com.skycloud.api.client.user;

import com.skycloud.api.dto.UserDTO;
import com.skycloud.common.base.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author sky
 **/
@FeignClient(name = "user" ,fallback = UserClient.UserClientFallback.class)
public interface UserClient {

    @RequestMapping(value = "/user/getUser",method = RequestMethod.GET)
    @ResponseBody
    Result<UserDTO> getUser(@RequestParam("username") String username, @RequestParam("password") String password);

    @Component
    class UserClientFallback implements UserClient {

        @Override
        public Result<UserDTO> getUser(@RequestParam("username") String username, @RequestParam("password") String password) {
            System.out.println("===========>>user client fallback:{}");
            return new Result<>();
        }
    }
}
