package com.skycloud.api.client.upload;

import com.skycloud.common.base.Result;
import lombok.Data;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author sky
 */
@FeignClient(name = "upload", fallback = UploadApi.UploadApiFallback.class)
public interface UploadApi {


	@RequestMapping(value="/upload/test/test",method=RequestMethod.POST)
	@ResponseBody
	Result<TestDTO> send(@RequestBody TestDTO testDTO);

	/**
	 * 断路器
	 * 
	 *
	 */
	@Component
	class UploadApiFallback  implements UploadApi {

		@Override
		public Result send(@RequestBody TestDTO testDTO) {
			return new Result();
		}



	}
	
	@Data
	class TestDTO {
		String name;
	}

}