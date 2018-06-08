package com.skycloud.auth.server.mapper;

import com.skycloud.auth.server.model.domain.AuthClient;
import com.skycloud.auth.server.model.domain.AuthClientService;
import com.skycloud.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AuthClientServiceMapper extends MyMapper<AuthClientService> {

    List<AuthClient> getAllowClientByServiceId(Long serviceId);

}