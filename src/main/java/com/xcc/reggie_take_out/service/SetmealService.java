package com.xcc.reggie_take_out.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xcc.reggie_take_out.dto.SetmealDto;
import com.xcc.reggie_take_out.entity.Setmeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Charles
 * @version 1.0
 * @className SetMealService
 */

@Service
public interface SetmealService extends IService<Setmeal> {

    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据
     * @param ids
     */
    void removeWithDish(List<Long> ids);
}
