package com.skycloud.auth.server.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skycloud.auth.server.model.domain.AuthClient;
import com.skycloud.auth.server.service.TestClientService;
import com.skycloud.base.ResponseVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    private TestClientService testClientService;

    @RequestMapping("/add")
    public ResponseVo add() {
        AuthClient authClient = testClientService.saveClient();
        return ResponseVo.ok(authClient);
    }

    @RequestMapping("/modify/{id}")
    public ResponseVo modify(@PathVariable("id") Long id){
        AuthClient client = testClientService.modifyClientById(id);
        return ResponseVo.ok(client);
    }

    @RequestMapping("/get")
    public ResponseVo get(){
        AuthClient client = new AuthClient();
        client.setCode("123");
        client.setId(19L);
        AuthClient authClient = testClientService.selectOne(client);
        return ResponseVo.ok(authClient);
    }

    @RequestMapping("/remove")
    public ResponseVo<Boolean> remove(){
        AuthClient client = new AuthClient();
        client.setCode("123");
        client.setName("456");
        int delete = testClientService.delete(client);
        return ResponseVo.ok(delete>0?true:false);
    }


    @RequestMapping("/getPage")
    public ResponseVo<PageInfo> getPage(Integer pageNum){
        AuthClient client = new AuthClient();
        client.setCode("123");
        PageHelper.startPage(pageNum,1);
        List<AuthClient> list = testClientService.select(client);
        PageInfo<AuthClient> pageInfo = new PageInfo<>(list);
        return  ResponseVo.ok(pageInfo);
    }


    @RequestMapping("/getExample")
    public ResponseVo getExample(){
        Example example = new Example(AuthClient.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("code", "123");
        List<AuthClient> list = testClientService.selectByExample(example);
        return ResponseVo.ok(list);
    }

}
