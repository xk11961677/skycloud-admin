package com.skycloud.permission.service.impl;

import com.skycloud.permission.common.service.impl.BaseServiceImpl;
import com.skycloud.permission.entity.UserRoleEntity;
import com.skycloud.permission.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
* 描述：</b><br>
* @author：系统生成
* @version:1.0
*/
@Repository
@Slf4j
public class UserRoleServiceImpl extends BaseServiceImpl implements UserRoleService {


   @Override
   public UserRoleEntity get(Integer id){
       UserRoleEntity userRoleEntity = new UserRoleEntity();
       userRoleEntity.setId(id);
       return (UserRoleEntity)get(userRoleEntity);
   }


   /*user customize code start*/

   /*user customize code end*/
}
