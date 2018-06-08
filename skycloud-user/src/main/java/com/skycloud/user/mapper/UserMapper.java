package com.skycloud.user.mapper;

import com.skycloud.common.mybatis.MyMapper;
import com.skycloud.user.model.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends MyMapper<User> {
}