package com.xcc.reggie_take_out.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xcc.reggie_take_out.common.R;
import com.xcc.reggie_take_out.dto.DishDto;
import com.xcc.reggie_take_out.entity.Dish;
import org.springframework.stereotype.Service;

/**
 * @author Charles
 * @version 1.0
 * @className DishService
 */
@Service
public interface DishService extends IService<Dish> {

    //新增菜品,同时插入菜品对应的口味数据,需要同时操作两张表:dish  dish_flavor
    void saveWithFlavor(DishDto dishDto);

    DishDto getByIdWithFlavor(Long id);

    //更新菜品信息同时还更新对应的口味信息
    void updateWithFlavor(DishDto dishDto);
}


