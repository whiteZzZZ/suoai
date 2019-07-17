package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.Chat;
import com.yiban.suoai.pojo.ChatExample;
import java.util.List;

public interface ChatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Chat record);

    int insertSelective(Chat record);

    List<Chat> selectByExample(ChatExample example);

    Chat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Chat record);

    int updateByPrimaryKey(Chat record);
}