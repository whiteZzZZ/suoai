package com.yiban.suoai.service.impl;

import com.github.pagehelper.PageHelper;
import com.yiban.suoai.mapper.*;
import com.yiban.suoai.pojo.*;
import com.yiban.suoai.service.TipService;
import com.yiban.suoai.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

@Service
public class TipServiceImpl implements TipService {

    @Autowired
    TipMapper tipMapper;
    @Autowired
    TipBankMapper tipBankMapper;
    @Autowired
    TipUserMapper tipUserMapper;
    @Autowired
    CyinforMapper cyinforMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    WordReviewMapper wordReviewMapper;

    @Override
    public int addTip(Tip tip) {
        return tipMapper.insert(tip);
    }

    @Override
    public int deleteTip(int id) {
        return tipMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int checkTip(int userId,List<TipBank> list) {
        List<Boolean> trueAns = (List)redisUtil.getObject("ans:"+userId);
        int count = 0;
        ListIterator<TipBank> iterator = list.listIterator(list.size());
        for(int i = trueAns.size()-1;i>=0;i--){
            if(trueAns.get(i) == iterator.previous().getAns())count++;
        }
        if (count>(list.size()*0.8)){
            redisUtil.delObject("ans:"+userId);
            list.forEach((n)->n.setStatus(1)); //审核通过
            tipMapper.updateBatch(list);

            User user = new User();  //更新考试用户违规状态
            user.setId(userId);
            user.setViolator(false);
            userMapper.updateByPrimaryKeySelective(user);

            List<TipModel> tipModelList = new ArrayList<>();
            TipModel tm = null;
            //更改被举报用户状态为违规
            for(Tip tip:list) {
                userId = 0;
                switch (tip.getSource()) {
                    case 1:
                        Cyinfor cyinfor = cyinforMapper.selectByPrimaryKey(tip.getSourceId());
                        userId = cyinfor.getUserId();
                        tm = new TipModel();
                        tm.setDelete(true);
                        tm.setName("cyinfor");
                        tm.setId(tip.getSourceId());
                        tipModelList.add(tm);
                        break;
                    case 2:
                        Review review = reviewMapper.selectByPrimaryKey(tip.getSourceId());
                        userId = review.getUserId();
                        tm = new TipModel();
                        tm.setDelete(true);
                        tm.setName("review");
                        tm.setId(tip.getSourceId());
                        tipModelList.add(tm);
                        break;
                    case 3:
                        WordReview wordReview = wordReviewMapper.selectByPrimaryKey(tip.getSourceId());
                        userId = wordReview.getUserId();
                        tm = new TipModel();
                        tm.setDelete(true);
                        tm.setName("word_review");
                        tm.setId(tip.getSourceId());
                        tipModelList.add(tm);
                        break;
                }
                User u = new User();
                u.setId(userId);
                u.setViolator(true);
                userMapper.updateByPrimaryKeySelective(user);
            }

            tipMapper.updateFlagBatch(tipModelList);  //更新被举报事件的删除标记

            return tipBankMapper.batchSaveOrUpdate(list);  //将题目添加进题库
        }
       return -1;
    }

    @Override
    public List<Tip> getExam(int userId) {
        TipUser tipUser = tipUserMapper.selectByPrimaryKey(userId);
        TipExample te = new TipExample();
        TipExample.Criteria tec = te.createCriteria();
        tec.andStatusEqualTo(0); //未审核

        int acturalCount = tipMapper.countByExample(te);  //实际未审核条数
        int acturalBankCount = tipBankMapper.countByExample(new TipBankExample());  //实际题库条数
        int count = tipUser.getNum()*5;    //总题数
        int tipCount = count/2;          //真实举报题数
        if(tipCount>acturalCount)tipCount = acturalCount;
        int bankCount = count-tipCount;  //题库题数

        int i = (int)(Math.random()*acturalBankCount);
        if(i+bankCount>acturalBankCount)i-=bankCount;
        if(i<0)i=0;

        PageHelper.offsetPage(i,  acturalBankCount);
        List<TipBank> ltb = tipBankMapper.selectByExample(new TipBankExample());
        List<Boolean> ans = ltb.stream().map(p->p.getAns()).collect(Collectors.toList());
        redisUtil.setObject("ans:"+userId,ans);

        PageHelper.offsetPage(0,tipCount);
        List<Tip> lt = tipMapper.selectByExample(te);
        lt.addAll(ltb);
        return lt;
    }

}
