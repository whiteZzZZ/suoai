package com.yiban.suoai.service.impl;

import com.yiban.suoai.forepojo.ForeLetter;
import com.yiban.suoai.forepojo.ForeSpaceLetter;
import com.yiban.suoai.mapper.LetterMapper;
import com.yiban.suoai.pojo.Letter;
import com.yiban.suoai.pojo.LetterExample;
import com.yiban.suoai.pojo.User;
import com.yiban.suoai.service.LetterService;
import com.yiban.suoai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class LetterServiceImpl implements LetterService {
    @Autowired
    LetterMapper letterMapper;
    @Autowired
    UserService userService;

    @Override
    public List<Letter> getOrderByTime(int userId) {
        LetterExample letterExample = new LetterExample();
        letterExample.createCriteria().andUserIdEqualTo(userId).andIsDeleteEqualTo(false);
        letterExample.setOrderByClause("`time` ASC,id ASC");
        return letterMapper.selectByExampleWithBLOBs(letterExample);
    }

    @Override
    public List<ForeLetter> full(List<Letter> letters) {
        List<ForeLetter> foreLetters = new ArrayList<>();
        for (Letter o : letters){
            ForeLetter foreLetter = new ForeLetter();
            foreLetter.setContent(o.getContent());
            foreLetter.setHeadLine(o.getHeadline());
            foreLetter.setId(o.getId());
            foreLetter.setHide(o.getHide());
            foreLetter.setMyself(o.getMyself());
            foreLetter.setTime(o.getTime());
            foreLetter.setPublish(o.getPublish());
            foreLetter.setUserId(o.getUserId());
            foreLetters.add(foreLetter);
        }
        return foreLetters;
    }

    @Override
    public Letter get(int letterId) {
        return letterMapper.selectByPrimaryKey(letterId);
    }

    @Override
    public void update(Letter letter) {
        letterMapper.updateByPrimaryKeyWithBLOBs(letter);
    }

    @Override
    public void insert(Letter letter) {
        letterMapper.insert(letter);
    }

    @Override
    public List<Letter> getSpaceLetter() {
        LetterExample letterExample = new LetterExample();
        letterExample.createCriteria().andIsDeleteEqualTo(false).andIsReadEqualTo(false).andMyselfEqualTo(false).andPublishEqualTo(true);
        return letterMapper.selectByExampleWithBLOBs(letterExample);
    }

    @Override
    public List<ForeSpaceLetter> full2(List<Letter> letters) {
        List<ForeSpaceLetter> foreSpaceLetters = new ArrayList<>();
        for(Letter o : letters){
            ForeSpaceLetter foreSpaceLetter = new ForeSpaceLetter();
            User user = userService.get(o.getUserId());
            foreSpaceLetter.setContent(o.getContent());
            foreSpaceLetter.setHeadLine(o.getHeadline());
            foreSpaceLetter.setHeadImg(user.getHeadImg());
            foreSpaceLetter.setTime(o.getTime());
            foreSpaceLetter.setUserId(o.getUserId());
            foreSpaceLetter.setUserName(user.getName());
            foreSpaceLetter.setId(o.getId());
            foreSpaceLetters.add(foreSpaceLetter);

        }
        return foreSpaceLetters;
    }
}
