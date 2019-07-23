package com.yiban.suoai.service;

import com.yiban.suoai.forepojo.ForeImform;
import com.yiban.suoai.pojo.Message;
import com.yiban.suoai.pojo.MessageExample;

import java.util.List;

public interface MessageService {

    int add(Message message);

    Message get(int id);

    int update(Message message);

    int delete(int id);

    List<Message> getByUserAndType(int userId,int type);

    List<ForeImform>  full(List<Message> messages);
}
