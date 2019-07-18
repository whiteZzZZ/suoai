package com.yiban.suoai.service.impl;

import com.yiban.suoai.mapper.CyinforMapper;
import com.yiban.suoai.pojo.Cyinfor;
import com.yiban.suoai.pojo.CyinforExample;
import com.yiban.suoai.pojo.User;
import com.yiban.suoai.service.CyinforService;
import com.yiban.suoai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CyinforServiceImpl  implements CyinforService {


    @Autowired
    CyinforMapper cyinforMapper;
    @Autowired
    UserService userService;

    @Override
    public Cyinfor get(int id) {
        return cyinforMapper.selectByPrimaryKey(id);
    }

    @Override
    public int add(Cyinfor cyinfor) {
        cyinforMapper.insertSelective(cyinfor);
        return cyinfor.getId();
    }

    @Override
    public void delete(int id) {
        cyinforMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Cyinfor full(int userid, Boolean privacy, Boolean hide, int who, int hasImage,String text) {
        User user =userService.get(userid);
        Cyinfor cyinfor = new Cyinfor();
        cyinfor.setUser_id(userid);
        cyinfor.setSchool_id(user.getSchool_id());
        cyinfor.setAcademy_id(user.getAcademy_id());
        cyinfor.setTime(new Date());
        cyinfor.setLike_time(0);
        cyinfor.setReview_time(0);
        cyinfor.setPrivacy(privacy);
        cyinfor.setHas_image(hasImage);
        cyinfor.setHide(hide);
        cyinfor.setWho(who);
        cyinfor.setPaper_id(user.getPaper());
        cyinfor.setText(text);
        return cyinfor;
    }

    @Override
    public List<Cyinfor> getAll() {
        CyinforExample example=new CyinforExample();
        example.createCriteria();
        example.setOrderByClause("id desc");
        List<Cyinfor > list =cyinforMapper.selectByExample(example);
        return list;
    }
}
