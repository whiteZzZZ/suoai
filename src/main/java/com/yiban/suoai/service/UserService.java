package com.yiban.suoai.service;


import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.forepojo.ForeRankUser;
import com.yiban.suoai.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    void add(User user);

    void update(User user);

    User get(int id);

    User selectByNameNum(String name , String num);

    /**
     * 用户增加经验值
     * @param userId
     * @param count
     * @return
     */
    int addExperience(int userId,int count);

    List<ForeRankUser> topTen() throws SAException;
}