package com.xcc.reggie_take_out.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xcc.reggie_take_out.entity.User;
import com.xcc.reggie_take_out.mapper.UserMapper;
import com.xcc.reggie_take_out.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Charles
 * @version 1.0
 * @className UserServiceImpl
 */

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
