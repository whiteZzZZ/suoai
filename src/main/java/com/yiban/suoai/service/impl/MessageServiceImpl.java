package com.yiban.suoai.service.impl;

import com.yiban.suoai.forepojo.ForeImform;
import com.yiban.suoai.mapper.MessageMapper;
import com.yiban.suoai.pojo.Cyinfor;
import com.yiban.suoai.pojo.Message;
import com.yiban.suoai.pojo.MessageExample;
import com.yiban.suoai.pojo.User;
import com.yiban.suoai.service.CyinforService;
import com.yiban.suoai.service.MessageService;
import com.yiban.suoai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Message> getByUserAndType(int userId,int type) {
        MessageExample example=new MessageExample();
        example.createCriteria().andUserIdEqualTo(userId).andTypeEqualTo(type);
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
           if(2==message.getType()||3==message.getType()){
               Cyinfor cyinfor= cyinforService.get(message.getCyId());
               foreImform.setText(cyinfor.getText());
           }

            list.add(foreImform);
        }
        return list;
    }
}
