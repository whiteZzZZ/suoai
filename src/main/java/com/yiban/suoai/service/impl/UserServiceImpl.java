package com.yiban.suoai.service.impl;

import com.yiban.suoai.mapper.UserMapper;
import com.yiban.suoai.pojo.User;
import com.yiban.suoai.pojo.UserExample;
import com.yiban.suoai.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public User selectByNameNum(String name, String num) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(name).andStu_numEqualTo(num);
        return userMapper.selectByExample(userExample).get(0);
    }

    @Override
    public void add(User user) {
        userMapper.insert(user);
    }


    @Override
    public void update(User users) {
        userMapper.updateByPrimaryKeySelective(users);
    }

    @Override
    public User get(int id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
