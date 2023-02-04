package com.xcc.reggie_take_out.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xcc.reggie_take_out.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Charles
 * @version 1.0
 * @className UserMapper
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
