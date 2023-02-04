package com.xcc.reggie_take_out.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xcc.reggie_take_out.dto.DishDto;
import com.xcc.reggie_take_out.entity.Dish;
import com.xcc.reggie_take_out.entity.DishFlavor;
import com.xcc.reggie_take_out.mapper.DishMapper;
import com.xcc.reggie_take_out.service.DishFlavorService;
import com.xcc.reggie_take_out.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Charles
 * @version 1.0
 * @className DishServiceImpl
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 新增菜品，同时保存对应的口味数据
     *
     * @param dishDto
     */
    @Transactional //涉及到对多张表的数据进行操作,需要加事务，需要事务生效,需要在启动类加上事务注解生效
    @Override
    public void saveWithFlavor(DishDto dishDto) {
        //保存菜品的基本信息到菜品表dish
        this.save(dishDto);

        //菜品id
        Long dishId = dishDto.getId();

        //菜品口味
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors = flavors.stream().map((item) -> {

            item.setDishId(dishId);
            return item;   //记得把数据返回去

        }).collect(Collectors.toList()); //把返回的集合搜集起来,用来被接收

        //把菜品口味的数据到口味表 dish_flavor  注意dish_flavor只是封装了name value 并没有封装dishId(从前端传过来的数据发现的,然而数据库又需要这个数据)
        dishFlavorService.saveBatch(dishDto.getFlavors()); //这个方法是批量保存


    }

    @Override
    public DishDto getByIdWithFlavor(Long id) {

        //查询菜品的基本信息  从dish表查询
        Dish dish = this.getById(id);

        //查询当前菜品对应的口味信息,从dish_flavor查询  条件查询
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);

        //然后把查询出来的flavors数据set进行 DishDto对象
        DishDto dishDto = new DishDto();
        //把dish表中的基本信息copy到dishDto对象，因为才创建的dishDto里面的属性全是空
        BeanUtils.copyProperties(dish, dishDto);
        dishDto.setFlavors(flavors);

        return dishDto;

    }

    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        //更新dish表的基本信息  因为这里的dishDto是dish的子类
        this.updateById(dishDto);

        //更新口味信息---》先清理再重新插入口味信息
        //清理当前菜品对应口味数据---dish_flavor表的delete操作
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(DishFlavor::getDishId, dishDto.getId());
        dishFlavorService.remove(queryWrapper);

        //添加当前提交过来的口味数据---dish_flavor表的insert操作
        List<DishFlavor> flavors = dishDto.getFlavors();

        //下面这段流的代码我注释,然后测试，发现一次是报dishId没有默认值(先测)，两次可以得到结果(后测，重新编译过，清除缓存过),相隔半个小时
        //因为这里拿到的flavorsz只有name和value(这是在设计数据封装的问题),不过debug测试的时候发现有时候可以拿到全部数据,有时候又不可以...  所以还是加上吧。。。。。
        flavors = flavors.stream().map((item) -> {
            item.setDishId(dishDto.getId());
            return item;
        }).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);

    }
}
