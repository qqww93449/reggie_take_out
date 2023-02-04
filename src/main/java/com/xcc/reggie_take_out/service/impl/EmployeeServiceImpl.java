package com.xcc.reggie_take_out.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xcc.reggie_take_out.entity.Employee;
import com.xcc.reggie_take_out.mapper.EmployeeMapper;
import com.xcc.reggie_take_out.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author Charles
 * @version 1.0
 * @className EmployeeServiceImpl
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService{
}
