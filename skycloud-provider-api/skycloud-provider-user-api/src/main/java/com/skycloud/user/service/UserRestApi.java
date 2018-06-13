package com.skycloud.user.service;

import com.skycloud.base.ResponseVo;
import com.skycloud.user.dto.UserDto;
import com.skycloud.user.service.hystrix.UserRestApiHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author sky
 **/
@FeignClient(value = "skycloud-provider-user" ,fallback = UserRestApiHystrix.class)
public interface UserRestApi {

    @PostMapping(value = "/user/getUser")
    @ResponseBody
    ResponseVo<UserDto> getUser(@RequestBody UserDto userDto);

}
