package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.ChatList;
import com.yiban.suoai.pojo.ChatListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChatListMapper {
    int countByExample(ChatListExample example);

    int deleteByExample(ChatListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChatList record);

    int insertSelective(ChatList record);

    List<ChatList> selectByExample(ChatListExample example);

    ChatList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ChatList record, @Param("example") ChatListExample example);

    int updateByExample(@Param("record") ChatList record, @Param("example") ChatListExample example);

    int updateByPrimaryKeySelective(ChatList record);

    int updateByPrimaryKey(ChatList record);
}