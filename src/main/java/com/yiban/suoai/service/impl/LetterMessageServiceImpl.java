package com.yiban.suoai.service.impl;

import com.yiban.suoai.forepojo.ForeLetterMessage;
import com.yiban.suoai.mapper.LetterMessageMapper;
import com.yiban.suoai.pojo.LetterMessage;
import com.yiban.suoai.pojo.LetterMessageExample;
import com.yiban.suoai.service.LetterMessageService;
import com.yiban.suoai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LetterMessageServiceImpl implements LetterMessageService {
    @Autowired
    LetterMessageMapper letterMessageMapper;
    @Autowired
    UserService userService;
    @Override
    public void insert(LetterMessage letterMessage) {
        letterMessageMapper.insert(letterMessage);
    }

    @Override
    public List<LetterMessage> get(int letterId) {
        LetterMessageExample letterMessageExample = new LetterMessageExample();
        letterMessageExample.createCriteria().andLetterIdEqualTo(letterId);
        return letterMessageMapper.selectByExample(letterMessageExample);
    }

    @Override
    public List<ForeLetterMessage> full(List<LetterMessage> letterMessages, int userId) {
        List<ForeLetterMessage> letterMessageList = new ArrayList<>();
        for(LetterMessage o : letterMessages){
            ForeLetterMessage foreLetterMessage = new ForeLetterMessage();
            foreLetterMessage.setId(o.getId());
            foreLetterMessage.setLetterId(o.getLetterId());
            foreLetterMessage.setMessage(o.getMessage());
            foreLetterMessage.setTime(o.getTime());
            foreLetterMessage.setUserId(o.getUserId());
            foreLetterMessage.setUserImg(userService.get(o.getUserId()).getHeadImg());
            foreLetterMessage.setUserName(userService.get(o.getUserId()).getName());
            letterMessageList.add(foreLetterMessage);
        }
        return letterMessageList;
    }
}
