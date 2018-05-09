package com.skycloud.upload;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


/**
 * 应用程序测试
 *
 * @author sky
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UploadApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class ApplicationTests {

	@Value("${local.server.port}")
	private int port = 0;

	/**
	 * 测试上传
	 *
	 * @throws InterruptedException
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void testUpload(){
		Resource resource = new ClassPathResource("banner.txt");

		MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<>();

		mvm.add("file", resource);
		mvm.add("user","test");
		ResponseEntity<Map> rs = new RestTemplate().postForEntity("http://localhost:" + port+"/upload/", mvm, Map.class);
		Assert.assertNotNull(rs.getBody().get("url"));

//		rs = new RestTemplate().postForEntity("http://localhost:" + port+"/upload/forkindeditor.htm", mvm, Map.class);
//		Assert.assertNotNull(rs.getBody().get("url"));
//
//		mvm.add("file",  new ClassPathResource("test1.jpg"));
//		rs = new RestTemplate().postForEntity("http://localhost:" + port+"/upload/multi", mvm, Map.class);
//		Assert.assertNotNull(rs.getBody().get("url"));
//
//		mvm.clear();
//		mvm.add("file", new ClassPathResource("empty.jpg"));
//		rs = new RestTemplate().postForEntity("http://localhost:" + port+"/upload/", mvm, Map.class);
//		Assert.assertNotNull(rs.getBody().get("url"));
//
//
//		mvm.clear();
//		mvm.add("file", new ClassPathResource("error.jsp"));
//		rs = new RestTemplate().postForEntity("http://localhost:" + port+"/upload/", mvm, Map.class);
//		Assert.assertNull(rs.getBody().get("url"));

	}

	/**
	 * 测试静态化
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void testStatic(){
		MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<>();
		mvm.add("url", ":"+port+"/upload/statictest");
		Map map = new RestTemplate().postForObject("http://localhost:"+port+"/upload/static",mvm,Map.class);
		Assert.assertTrue(map.containsKey("url"));

		mvm.clear();
		mvm.add("url", ":"+port+"/upload/statictest");
		map = new RestTemplate().postForObject("http://localhost:"+port+"/upload/static",mvm,Map.class);
		Assert.assertTrue(map.containsKey("url"));

		mvm.clear();
		mvm.add("url", "http://news.163.com/16/0701/07/BQSEI2FC00014PRF.html");
		map = new RestTemplate().postForObject("http://localhost:"+port+"/upload/static",mvm,Map.class);
		Assert.assertTrue(map.containsKey("url"));
	}
}