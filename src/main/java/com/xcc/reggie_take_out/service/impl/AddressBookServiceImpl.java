package com.xcc.reggie_take_out.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xcc.reggie_take_out.entity.AddressBook;
import com.xcc.reggie_take_out.mapper.AddressBookMapper;
import com.xcc.reggie_take_out.service.AddressBookService;
import org.springframework.stereotype.Service;

/**
 * @author Charles
 * @version 1.0
 * @className addressBookServiceImpl
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
