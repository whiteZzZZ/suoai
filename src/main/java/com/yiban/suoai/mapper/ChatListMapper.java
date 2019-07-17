package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.ChatList;
import com.yiban.suoai.pojo.ChatListExample;
import java.util.List;

public interface ChatListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChatList record);

    int insertSelective(ChatList record);

    List<ChatList> selectByExample(ChatListExample example);

    ChatList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChatList record);

    int updateByPrimaryKey(ChatList record);
}