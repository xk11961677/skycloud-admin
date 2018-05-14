package com.skycloud.permission.service.impl;

import com.skycloud.permission.common.service.impl.BaseServiceImpl;
import com.skycloud.permission.entity.RoleEntity;
import com.skycloud.permission.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
* 描述：</b><br>
* @author：系统生成
* @version:1.0
*/
@Repository
@Slf4j
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {


   @Override
   public RoleEntity get(Integer id){
       RoleEntity roleEntity = new RoleEntity();
       roleEntity.setId(id);
       return (RoleEntity)get(roleEntity);
   }


   /*user customize code start*/

   /*user customize code end*/
}
