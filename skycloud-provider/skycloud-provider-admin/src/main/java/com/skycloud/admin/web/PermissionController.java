package com.skycloud.admin.web;

import com.skycloud.base.ResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sky
 * @description
 * @create 2018-05-08 下午9:10
 **/
@RestController
@RequestMapping("permission")
public class PermissionController {

    @RequestMapping("getAdmin")
    public ResponseData getAdmin() {
        return ResponseData.ok();
    }
}
