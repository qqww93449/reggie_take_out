package com.xcc.reggie_take_out.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xcc.reggie_take_out.entity.ShoppingCart;
import com.xcc.reggie_take_out.mapper.ShoppingCartMapper;
import com.xcc.reggie_take_out.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Charles
 * @version 1.0
 * @className ShoppingCartServiceImpl
 */

@Slf4j
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
