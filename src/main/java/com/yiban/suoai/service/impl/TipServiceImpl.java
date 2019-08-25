package com.yiban.suoai.service.impl;

import com.github.pagehelper.PageHelper;
import com.yiban.suoai.forepojo.ForeTip;
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
    @Autowired
    ImageMapper imageMapper;

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
        if(trueAns == null){
            return -2;
        }
        ListIterator<TipBank> iterator = list.listIterator(list.size());
        for(int i = trueAns.size()-1;i>=0;i--){
            TipBank tt = iterator.previous();
            System.out.println("ans="+tt.getAns());
            if(trueAns.get(i).equals(tt.getAns())){
                count++;
                System.out.println("match ++");
            }
        }
        if (count>(trueAns.size()*0.8)){
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
            for(int i = 0;i<list.size()-trueAns.size();i++) {
                Tip tip = list.get(i);
                userId = 0;
                System.out.println("tipList=");
                list.forEach(n-> System.out.println(n.getId()));
                switch (tip.getSource()) {
                    case 1:
                        System.out.println("in cyinfor tip");
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
                int tn = tipUserMapper.selectByPrimaryKey(userId).getNum();
                TipUser tu = new TipUser();
                tu.setId(userId);
                //违规次数+1，先关闭，设置为一直为1
                //tu.setNum(tn+1);
                tu.setNum(1);
                tipUserMapper.updateByPrimaryKeySelective(tu);
            }
            if(!tipModelList.isEmpty())
                tipMapper.updateFlagBatch(tipModelList);  //更新被举报事件的删除标记(如果有不是题库的题）

            return tipBankMapper.batchSaveOrUpdate(list);  //将题目添加进题库
        }
       return -1;
    }

    @Override
    public List<ForeTip> getExam(int userId) {
        TipUser tipUser = tipUserMapper.selectByPrimaryKey(userId);
        if(tipUser == null){
            List<ForeTip> tipList = new ArrayList<>();
            return tipList;
        }
        TipExample te = new TipExample();
        TipExample.Criteria tec = te.createCriteria();
        tec.andStatusEqualTo(0); //未审核

        int acturalCount = tipMapper.countByExample(te);  //实际未审核条数
        int acturalBankCount = tipBankMapper.countByExample(new TipBankExample());  //实际题库条数
        int count = tipUser.getNum()*5;    //总题数
        int tipCount = count/2;          //真实举报题数
        if(tipCount>acturalCount)tipCount = acturalCount;
        System.out.println("tipCount="+tipCount);
        System.out.println("acturalCount="+acturalCount);
        int bankCount = count-tipCount;  //题库题数

        int i = (int)(Math.random()*acturalBankCount);
        if(i+bankCount>acturalBankCount)i-=bankCount;
        if(i<0)i=0;

        PageHelper.offsetPage(i,  bankCount);
        List<TipBank> ltb = tipBankMapper.selectByExample(new TipBankExample());
        List<Boolean> ans = ltb.stream().map(p->p.getAns()).collect(Collectors.toList());
        redisUtil.setObject("ans:"+userId,ans);

        PageHelper.offsetPage(0,tipCount);
        List<Tip> lt = tipMapper.selectByExample(te);
        System.out.println("acNum="+acturalBankCount);
        System.out.println("lt==");
        lt.forEach(n->System.out.println(n.getId()));
        System.out.println("ltb==");
        ltb.forEach(n->n.setAns(null));
        lt.addAll(ltb);
        return addImage(lt);

    }

    private List<ForeTip> addImage(List<Tip> lt){
        List<ForeTip> list = new ArrayList<>();
        for(Tip p : lt){
            if(p.getSource().equals(1)){
                //是传阅
                Cyinfor c = cyinforMapper.selectByPrimaryKey(p.getSourceId());
                if(c != null){
                    //如果传阅不为空，检查是否有图片
                    if(c.getHasImage()>0){
                        ImageExample ie = new ImageExample();
                        ImageExample.Criteria iec = ie.createCriteria();
                        iec.andCyIdEqualTo(c.getId());
                        List<Image> imgList = imageMapper.selectByExample(ie);
                        ForeTip fp = new ForeTip(p,imgList.get(0).getUrl());
                        list.add(fp);
                    }
                }
            }
        }
        return list;
    }

}
