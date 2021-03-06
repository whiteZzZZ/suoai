package com.yiban.suoai.service;

import com.yiban.suoai.forepojo.ForeChatList;
import com.yiban.suoai.pojo.Chat;
import com.yiban.suoai.pojo.ChatList;

import java.util.List;

public interface ChatService {
    List<ChatList> getChatList(int userId);
    List<Chat> getChatRecord(int cuId);
    int addChatRoom(int userId1,int userId2);
    int getAddChatRoom(int userId1,int userId2);
    List<ForeChatList> getFore(List<ChatList> list);
    int deleteRecord(int cuId);
    int deleteChatList(int id);
}
