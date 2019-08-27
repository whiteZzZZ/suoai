package com.yiban.suoai.service.impl;

import com.yiban.suoai.forepojo.ForeChatList;
import com.yiban.suoai.mapper.ChatListMapper;
import com.yiban.suoai.mapper.ChatMapper;
import com.yiban.suoai.mapper.UserMapper;
import com.yiban.suoai.pojo.*;
import com.yiban.suoai.service.ChatService;
import com.yiban.suoai.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    ChatListMapper chatListMapper;
    @Autowired
    ChatMapper chatMapper;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    UserMapper userMapper;

    static final int timeout = 10800; //3小时

    @Override
    public List<ChatList> getChatList(int userId) {
        ChatListExample ce = new ChatListExample();
        ChatListExample.Criteria cec = ce.createCriteria();
        ce.or().andUserId1EqualTo(userId);
        ce.or().andUserId2EqualTo(userId);
        List<ChatList> list = chatListMapper.selectByExample(ce);
        for(ChatList cl : list){
            if(cl.getUserId2()==userId){
                cl.setUserId2(cl.getUserId1());
                cl.setUserId1(userId);
            }
        }
        //调换位置   1为自己，2为对方
        return list;
    }

    @Override
    public List<Chat> getChatRecord(int cuId) {
        ChatExample ce = new ChatExample();
        ce.setOrderByClause("time ASC");
        ChatExample.Criteria cec = ce.createCriteria();
        cec.andCuIdEqualTo(cuId);
        List<Chat> list = chatMapper.selectByExample(ce);
        redisUtil.setObject("room:"+cuId,list, timeout);
        List<Chat> tmp= redisUtil.getObjList("room:update:");
        list.addAll(tmp);
        return list;
    }

    @Override
    public int addChatRoom(int userId1, int userId2) {
        if(userId1>userId2){
            int tmp = userId2;userId2=userId1;userId1=tmp;
        }
        ChatList cl = new ChatList();
        cl.setUserId1(userId1);
        cl.setUserId2(userId2);
        chatListMapper.insert(cl);
        return cl.getId();
    }

    @Override
    public int getAddChatRoom(int userId1, int userId2) {
        if(userId1>userId2){
            int tmp = userId2;userId2=userId1;userId1=tmp;
        }
        ChatListExample cle = new ChatListExample();
        ChatListExample.Criteria clec = cle.createCriteria();
        clec.andUserId1EqualTo(userId1);
        clec.andUserId2EqualTo(userId2);
        List<ChatList> list =chatListMapper.selectByExample(cle);
        if(!list.isEmpty()){
            return list.get(0).getId();
        }else{
            return addChatRoom(userId1,userId2);
        }
    }

    @Override
    public List<ForeChatList> getFore(List<ChatList> list) {
        List<ForeChatList> result = new ArrayList<>();
        for(ChatList cl : list){
            ForeChatList fcl = new ForeChatList(cl);
            User u  = userMapper.selectByPrimaryKey(cl.getUserId2());
            if(u != null){
                fcl.setUrl(u.getHeadImg());
            }
            ChatExample ce = new ChatExample();
            ChatExample.Criteria cec = ce.createCriteria();
            cec.andCuIdEqualTo(cl.getId());
            ce.setOrderByClause("time DESC");
            List<Chat> rr = chatMapper.selectByExample(ce);
            if(!rr.isEmpty()){
                Chat chat = rr.get(0);
                fcl.setMessage(chat.getContent());
                fcl.setTime(chat.getTime());
            }
            result.add(fcl);
        }
        return result;
    }

    @Override
    public int deleteRecord(int cuId) {
        if(redisUtil.hasObjectKey("room:"+cuId)){
            redisUtil.delObject("room:"+cuId);
        }
        ChatExample ce = new ChatExample();
        ChatExample.Criteria cec = ce.createCriteria();
        cec.andCuIdEqualTo(cuId);
        return chatMapper.deleteByExample(ce);
    }

    @Override
    public int deleteChatList(int id) {
        return chatListMapper.deleteByPrimaryKey(id);
    }
}
