package com.skycloud.auth.server;

import com.skycloud.auth.server.model.domain.AuthClient;
import com.skycloud.auth.server.service.TestClientService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;


/**
 * 应用程序测试
 *
 * @author sky
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AuthApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@Slf4j
public class ApplicationTests {

	@Value("${local.server.port}")
	private int port = 0;

	@Resource
	private TestClientService testClientService;

	/**
	 * 测试上传
	 *
	 * @throws InterruptedException
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void testAdd(){
		AuthClient client = new AuthClient();
		client.setCode("123");
		client.setName("456");
		testClientService.save(client);
		log.info("",client);
	}


	@SuppressWarnings("rawtypes")
	@Test
	public void testModify(){
		AuthClient client = new AuthClient();
		client.setCode("123");
		client.setName("789");
		testClientService.update(client);
		log.info("",client);
	}


	@SuppressWarnings("rawtypes")
	@Test
	public void testGet(){
		AuthClient client = new AuthClient();
		client.setCode("123");
		testClientService.select(client);
		log.info("",client);
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void testRemove(){
		AuthClient client = new AuthClient();
		client.setCode("123");
		client.setName("456");
		testClientService.delete(client);
		log.info("",client);
	}





}