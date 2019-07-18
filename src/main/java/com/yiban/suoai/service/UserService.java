package com.yiban.suoai.service;


import com.yiban.suoai.pojo.User;
import org.springframework.stereotype.Service;


public interface UserService {

    void add(User user);

    void update(User user);

    User get(int id);


}
