package com.skycloud.user.service.hystrix;

import com.skycloud.base.ResponseVo;
import com.skycloud.user.dto.UserDto;
import com.skycloud.user.service.UserRestApi;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author sky
 **/
@Component
public class UserRestApiHystrix implements UserRestApi {

    @Override
    public ResponseVo<UserDto> getUser(@RequestBody UserDto userDto) {
        return new ResponseVo<>();
    }
}
