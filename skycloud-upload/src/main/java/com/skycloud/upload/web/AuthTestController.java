package com.skycloud.upload.web;

import com.skycloud.base.common.base.Result;
import com.skycloud.base.common.client.UploadClient;
import com.skycloud.base.common.client.UserClient;
import com.skycloud.base.common.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author sky
 **/
@Controller
@RequestMapping("authTest")
public class AuthTestController {

    @Autowired
    @SuppressWarnings(value={"all"})
    private UserClient userClient;

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
        Result<UserDTO> user = userClient.getUser("111", "111");
        return user;
    }
}
