package com.xcc.reggie_take_out.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xcc.reggie_take_out.entity.Category;

/**
 * @author Charles
 * @version 1.0
 * @className CategoryService
 */


//在CategoryService中定义自己需要的方法，直接写就行
public interface CategoryService extends IService<Category> {

    void remove(Long ids);

}
