package com.xcc.reggie_take_out.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xcc.reggie_take_out.entity.Orders;

/**
 * @author Charles
 * @version 1.0
 * @className OrdersService
 */

public interface OrderService extends IService<Orders> {
    void submit(Orders orders);
}
