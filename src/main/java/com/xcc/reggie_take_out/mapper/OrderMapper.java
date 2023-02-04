package com.xcc.reggie_take_out.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xcc.reggie_take_out.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Charles
 * @version 1.0
 * @className OrderMapper
 */
@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}
