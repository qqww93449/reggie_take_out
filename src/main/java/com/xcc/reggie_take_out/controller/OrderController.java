package com.xcc.reggie_take_out.controller;

import com.xcc.reggie_take_out.common.R;
import com.xcc.reggie_take_out.entity.Orders;
import com.xcc.reggie_take_out.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Charles
 * @version 1.0
 * @className OrderController
 */
@Slf4j
@RestController
@RequestMapping("order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    /**
     * 用户下单
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        log.info("订单数据：{}",orders);
        orderService.submit(orders);
        return R.success("下单成功");
    }
}
