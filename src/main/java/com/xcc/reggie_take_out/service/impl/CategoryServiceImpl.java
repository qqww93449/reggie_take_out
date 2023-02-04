package com.xcc.reggie_take_out.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xcc.reggie_take_out.common.CustomException;
import com.xcc.reggie_take_out.entity.Category;
import com.xcc.reggie_take_out.entity.Dish;
import com.xcc.reggie_take_out.entity.Setmeal;
import com.xcc.reggie_take_out.mapper.CategoryMapper;
import com.xcc.reggie_take_out.service.CategoryService;
import com.xcc.reggie_take_out.service.DishService;
import com.xcc.reggie_take_out.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Charles
 * @version 1.0
 * @className CategoryServiceImpl
 */

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    DishService dishService;

    @Autowired
    SetmealService setMealService;

    /**
     * 根据id删除 分类，删除之前需要进行判断是否有关联数据
     *
     * @param ids
     */
    @Override
    public void remove(Long ids) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, ids);
        //注意:这里使用count方法的时候一定要传入条件查询的对象，否则计数会出现问题，计算出来的是全部的数据的条数
        int count = dishService.count(dishLambdaQueryWrapper);

        //查询当前分类是否关联了菜品，如果已经管理，直接抛出一个业务异常
        if (count > 0) {
            //已经关联了菜品，抛出一个业务异常
            throw new CustomException("当前分类项关联了菜品,不能删除");
        }

        //查询当前分类是否关联了套餐，如果已经管理，直接抛出一个业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, ids);
        //注意:这里使用count方法的时候一定要传入条件查询的对象，否则计数会出现问题，计算出来的是全部的数据的条数
        int setmealCount = setMealService.count(setmealLambdaQueryWrapper);
        if (setmealCount > 0) {
            //已经关联了套餐，抛出一个业务异常
            throw new CustomException("当前分类项关联了套餐,不能删除");
        }
        //正常删除
        super.removeById(ids);

    }
}
