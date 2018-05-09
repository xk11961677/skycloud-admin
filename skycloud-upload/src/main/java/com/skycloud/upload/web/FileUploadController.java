package com.skycloud.upload.web;

import com.skycloud.upload.configuration.OssConfiguration;
import com.skycloud.upload.constant.UploadConstant;
import com.skycloud.upload.util.ALOssUtils;
import com.skycloud.upload.util.JodaUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传控制器. <br>
 * 用于文件上传，提供kindeditor的上传支持.
 * @author sky
 *
 */

@RestController
@Slf4j
public class FileUploadController {

	@Resource
	private OssConfiguration oc;

	@Resource
	private ALOssUtils alOssUtils;

	/**
	 * 上传文件 由于ie 浏览器兼容性问题, 对于较低版本的IE 浏览器 采用iframe 上传文件后, 返回 json格式数据
	 *
	 * @param req
	 *            HTTP请求
	 * @param path
	 *            上传文件路径
	 * @param file
	 *            上传文件
	 * @return 结果
	 */
	@RequestMapping(value = { "/", "/file" }, method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upload(HttpServletRequest req,String path,String user, @RequestParam MultipartFile file) {
		Map<String,Object> map = new HashMap<>();
		map.put("code", UploadConstant.ERR_CODE_9999);
		map.put("msg","系统异常");
		map.put("url","");
		if(StringUtils.isEmpty(path) || StringUtils.isEmpty(user)) {
			map.put("code", UploadConstant.ERR_CODE_11000);
			map.put("msg","参数错误");
			return map;
		}
		try {
			String url = alOssUtils.uploadOss(path,user+"-"+file.getOriginalFilename(),file.getInputStream());
			map.put("code",UploadConstant.SUCC_CODE_10000);
			map.put("msg","上传文件成功");
			map.put("url",url);
		} catch (Exception e) {
			log.error("上传文件出现异常:{}",e);
		}
		return map;
	}

	/**
	 * 多文件上传
	 * 
	 * @param req
	 *            HTTP请求
	 * @param key
	 *            上传类型
	 * @param file
	 *            上传文件
	 * @return 结果
	 */
	@RequestMapping(value = "/multi", method = RequestMethod.POST)
	public Map<String, Object> upload(HttpServletRequest req,String user, String key, @RequestParam MultipartFile[] file) {

		return new HashMap<>();
	}

	/**
	 * kindeditor上传支持
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "forkindeditor", method = RequestMethod.POST)
	public Map<String, Object> upload(HttpServletRequest req) {
		Map<String,Object> map = new HashMap<>();
		map.put("error", UploadConstant.ERR_CODE_9999);
		map.put("message","系统异常");
		try {
			MultipartHttpServletRequest r = (MultipartHttpServletRequest) req;
			Collection<MultipartFile> files = r.getFileMap().values();
			if (!files.isEmpty()) {
				MultipartFile file = files.iterator().next();
				String url = alOssUtils.uploadOss("kindeditor", JodaUtils.currentDateToInt()+file.getOriginalFilename(),file.getInputStream());
				map.clear();
				map.put("error", "0");
				map.put("message","成功");
				map.put("url",alOssUtils.getOssURL(url));
			}
		} catch (Exception e) {
			log.error("fkeditor 上传文件错误:{}",e);
		}
		return map;
	}
}
