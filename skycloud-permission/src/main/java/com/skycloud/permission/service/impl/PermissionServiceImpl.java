package com.skycloud.permission.service.impl;

import com.skycloud.permission.common.service.impl.BaseServiceImpl;
import com.skycloud.permission.entity.PermissionEntity;
import com.skycloud.permission.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
* 描述：</b><br>
* @author：系统生成
* @version:1.0
*/
@Repository
@Slf4j
public class PermissionServiceImpl extends BaseServiceImpl implements PermissionService {


   @Override
   public PermissionEntity get(Integer id){
       PermissionEntity permissionEntity = new PermissionEntity();
       permissionEntity.setId(id);
       return (PermissionEntity)get(permissionEntity);
   }


   /*user customize code start*/

   /*user customize code end*/
}
