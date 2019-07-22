package com.yiban.suoai.service;

import com.yiban.suoai.pojo.Message;
import com.yiban.suoai.pojo.MessageExample;

public interface MessageService {

    int add(Message message);

    Message get(int id);

    int update(Message message);

    int delete(int id);
}
