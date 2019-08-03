package com.yiban.suoai.service.impl;

import com.yiban.suoai.mapper.UserMapper;
import com.yiban.suoai.pojo.User;
import com.yiban.suoai.pojo.UserExample;
import com.yiban.suoai.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public User selectByNameNum(String name, String num) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(name).andStuNumEqualTo(num);
        return userMapper.selectByExample(userExample).get(0);
    }

    @Override
    public int addExperience(int userId, int count) {
        User user=userMapper.selectByPrimaryKey(userId);
        int experience=user.getExperience();
        int nowExperience=experience+count;
        user.setExperience(nowExperience);//记得后面要更新数据库
        int upgradeExperience=1;//所需要的升级经验
        int upgrade=0;//用户是否升级  1为升级了
        for(int i=1;i<=10;i++){
            if(experience<upgradeExperience&&nowExperience>=upgradeExperience){
                //如果用户升级了
                upgrade=1;
                user.setLevel(user.getLevel()+1);//升级
                break;
            }
            upgradeExperience*=2;
        }
        update(user);
        return upgrade;
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
