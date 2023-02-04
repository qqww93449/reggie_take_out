package com.xcc.reggie_take_out.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xcc.reggie_take_out.entity.Dish;
import com.xcc.reggie_take_out.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Charles
 * @version 1.0
 * @className DishMapper
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
