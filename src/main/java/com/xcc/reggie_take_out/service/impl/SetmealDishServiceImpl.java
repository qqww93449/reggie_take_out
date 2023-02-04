package com.xcc.reggie_take_out.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xcc.reggie_take_out.entity.SetmealDish;
import com.xcc.reggie_take_out.mapper.SetmealDishMapper;
import com.xcc.reggie_take_out.service.SetmealDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Charles
 * @version 1.0
 * @className SetmealDishServiceImpl
 */
@Service
@Slf4j
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish> implements  SetmealDishService{
}
