package com.skycloud.permission.service.impl;

import com.skycloud.permission.common.service.impl.BaseServiceImpl;
import com.skycloud.permission.entity.UserEntity;
import com.skycloud.permission.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
* 描述：</b><br>
* @author：系统生成
* @version:1.0
*/
@Repository
@Slf4j
public class UserServiceImpl extends BaseServiceImpl implements UserService {


   @Override
   public UserEntity get(Integer id){
       UserEntity userEntity = new UserEntity();
       userEntity.setId(id);
       return (UserEntity)get(userEntity);
   }


   /*user customize code start*/

   /*user customize code end*/
}
