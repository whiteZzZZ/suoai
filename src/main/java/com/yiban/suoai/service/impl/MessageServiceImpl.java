package com.yiban.suoai.service.impl;

import com.yiban.suoai.forepojo.ForeCyinfor;
import com.yiban.suoai.forepojo.ForeImform;
import com.yiban.suoai.mapper.MessageMapper;
import com.yiban.suoai.pojo.*;
import com.yiban.suoai.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;

@Service
public class MessageServiceImpl implements MessageService {


    @Autowired
    MessageMapper messageMapper;
    @Autowired
    UserService userService;
    @Autowired
    CyinforService cyinforService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    WordReviewService wordReviewService;

    @Override
    public int add(Message message) {
        return messageMapper.insert(message);
    }

    @Override
    public Message get(int id) {
        return messageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Message message) {
        return messageMapper.updateByPrimaryKeySelective(message);
    }

    @Override
    public int delete(int id) {
        return messageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Message> getByUserAndType(int userId,int ...type) {
        MessageExample example=new MessageExample();
        if(type.length==1){
            example.createCriteria().andUserIdEqualTo(userId).andTypeEqualTo(type[0]);
        }else if(type.length==3){
            List<Integer> list= new ArrayList<>();
            list.add(type[0]);
            list.add(type[1]);
            list.add(type[2]);
            example.createCriteria().andUserIdEqualTo(userId).andTypeIn(list);
        }else if(type.length==2){
            List<Integer> list= new ArrayList<>();
            list.add(type[0]);
            list.add(type[1]);
            example.createCriteria().andUserIdEqualTo(userId).andTypeIn(list);
        }

        example.setOrderByClause("id desc");
        return messageMapper.selectByExample(example);
    }

    @Override
    public List<ForeImform> full(List<Message> messages) {
        List<ForeImform> list=new ArrayList<>();
        for(Message message:messages){
            ForeImform foreImform=new ForeImform();
            foreImform.setCyId(message.getCyId());
            foreImform.setUserId(message.getUserId());
            foreImform.setSponsorId(message.getSponsorId());
            foreImform.setTime(message.getTime());
            foreImform.setType(message.getType());
            if(0!=message.getSponsorId()){
                //如果对方不是匿名的话
                User user=userService.get(message.getSponsorId());
                foreImform.setHeadImg(user.getHeadImg());
                foreImform.setName(user.getName());
            }
           /* if(1==message.getType()||0==message.getType()){
                //如果收到的是表白
                Cyinfor cyinfor= cyinforService.get(message.getCyId());
                if(0==message.getType()&&0!=message.getSponsorId()){
                    //公开 不匿名表白
                    foreImform.setText("公开表白");
                }
                if(0==message.getType()&&0==message.getSponsorId()){
                    //公开 不匿名表白
                    foreImform.setText("公开匿名表白");
                }
                if(1==message.getType()&&0!=message.getSponsorId()){
                    foreImform.setText("私密表白");
                }
                if(1==message.getType()&&0==message.getSponsorId()){
                    foreImform.setText("私密匿名表白");
                }
            }
*/
           //如果是点赞和评论的话就要显示出内容出来
           if(6==message.getType()){
               Cyinfor cyinfor= cyinforService.get(message.getCyId());
               foreImform.setText(cyinfor.getText());
           }


           if(7==message.getType()||3==message.getType()){
               //评论的赞或者评论
               Review review=reviewService.get(message.getCyId());
               foreImform.setText(review.getContent());
           }
           if(8==message.getType()){
                WordReview wordReview=wordReviewService.get(message.getCyId());
                foreImform.setText(wordReview.getContent());

           }

            list.add(foreImform);
        }
        return list;
    }

    @Override
    public int deleteByAll(int type, int cyId, int sponsorId, int userId) {
        MessageExample example=new MessageExample();
        example.createCriteria().andTypeEqualTo(type).andCyIdEqualTo(cyId).andSponsorIdEqualTo(sponsorId).andUserIdEqualTo(userId);
        return messageMapper.deleteByExample(example);
    }
}
