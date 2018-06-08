package com.skycloud.auth.server.mapper;

import com.skycloud.auth.server.model.domain.AuthClient;
import com.skycloud.common.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AuthClientMapper extends MyMapper<AuthClient> {
}