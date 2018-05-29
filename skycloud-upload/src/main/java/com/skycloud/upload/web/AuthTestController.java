package com.skycloud.upload.web;

import com.skycloud.api.client.upload.UploadClient;
import com.skycloud.api.client.user.UserClient;
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
    private UserClient userClient;

    @Resource
    private OssConfiguration oc;

    /**
     *
     * @param testDTO
     * @return
     */
    @RequestMapping("test")
    @ResponseBody
    public Result<UploadClient.TestDTO> test(@RequestBody UploadClient.TestDTO testDTO) {
        System.out.println("===========>>:{}"+testDTO.getName());
        UploadClient.TestDTO dto = new UploadClient.TestDTO();
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
        Result<UserDTO> user = userClient.getUser("admin", "admin");
        return user;
    }
}
