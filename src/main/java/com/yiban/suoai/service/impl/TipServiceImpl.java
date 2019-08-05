package com.yiban.suoai.service.impl;

import com.github.pagehelper.PageHelper;
import com.yiban.suoai.mapper.CyinforMapper;
import com.yiban.suoai.mapper.TipBankMapper;
import com.yiban.suoai.mapper.TipMapper;
import com.yiban.suoai.mapper.TipUserMapper;
import com.yiban.suoai.pojo.*;
import com.yiban.suoai.service.TipService;
import com.yiban.suoai.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

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
    RedisUtil redisUtil;

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
       // int count =
       return 0;
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
