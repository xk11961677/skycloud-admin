package com.skycloud.core.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


/**
 * The interface My mapper.
 *
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
