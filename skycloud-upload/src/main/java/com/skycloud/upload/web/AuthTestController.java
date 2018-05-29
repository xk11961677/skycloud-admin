package com.skycloud.upload.web;

import com.skycloud.api.client.upload.UploadApi;
import com.skycloud.api.client.user.UserApi;
import com.skycloud.api.dto.UserDTO;
import com.skycloud.common.base.Result;
import com.skycloud.upload.configuration.OssConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

/**
 * @author sky
 **/
@Controller
@RequestMapping("authTest")
public class AuthTestController {

    @Resource
    private UserApi userApi;

    @Resource
    private OssConfiguration oc;

    /**
     *
     * @param testDTO
     * @return
     */
    @RequestMapping("test")
    @ResponseBody
    public Result<UploadApi.TestDTO> test(@RequestBody UploadApi.TestDTO testDTO) {
        System.out.println("===========>>:{}"+testDTO.getName());
        UploadApi.TestDTO dto = new UploadApi.TestDTO();
        dto.setName("result name 123");
        return Result.getSuccessResult(dto);
    }

    /**
     * 服务之间鉴权
     * @return
     */
    @RequestMapping("authTest")
    @ResponseBody
    public Result<UserDTO> authTest() {
        oc.getAliyunAccessKeyId();
        Result<UserDTO> user = userApi.getUser("admin", "admin");
        return user;
    }
}
