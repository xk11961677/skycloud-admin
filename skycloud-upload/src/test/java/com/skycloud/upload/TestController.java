package com.skycloud.upload;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试用控制器
 * @author sky
 *
 */
@RestController
public class TestController {

	/**
	 * 静态化测试用
	 * @return
	 */
	@RequestMapping("/statictest")
	public String statictest(){
		return "hello";
	}
}
