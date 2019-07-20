package com.yiban.suoai.service.impl;

import com.yiban.suoai.forepojo.ForeCyinfor;
import com.yiban.suoai.mapper.CyinforMapper;
import com.yiban.suoai.pojo.*;
import com.yiban.suoai.service.CyinforService;
import com.yiban.suoai.service.ImageService;
import com.yiban.suoai.service.LikeInfoService;
import com.yiban.suoai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CyinforServiceImpl  implements CyinforService {


    @Autowired
    CyinforMapper cyinforMapper;
    @Autowired
    UserService userService;
    @Autowired
    ImageService imageService;
    @Autowired
    LikeInfoService likeInfoService;

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
    public void update(Cyinfor cyinfor) {
        cyinforMapper.updateByPrimaryKeySelective(cyinfor);
    }

    @Override
    public Cyinfor full(int userid, Boolean privacy, Boolean hide, int who, int hasImage,String text) {
        User user =userService.get(userid);
        Cyinfor cyinfor = new Cyinfor();
        cyinfor.setUserId(userid);
        cyinfor.setSchoolId(user.getSchoolId());
        cyinfor.setAcademyId(user.getAcademyId());
        cyinfor.setTime(new Date());
        cyinfor.setLikeTime(0);
        cyinfor.setReviewTime(0);
        cyinfor.setPrivacy(privacy);
        cyinfor.setHasImage(hasImage);
        cyinfor.setHide(hide);
        cyinfor.setWho(who);
        cyinfor.setPaperId(user.getPaper());
        cyinfor.setText(text);
        return cyinfor;
    }

    @Override
    public List<Cyinfor> getAll() {
        CyinforExample example=new CyinforExample();
        example.createCriteria();
        example.setOrderByClause("id desc");
        List<Cyinfor > list =cyinforMapper.selectByExampleWithBLOBs(example);
        return list;
    }

    @Override
    public List<ForeCyinfor> foreFull(List<Cyinfor> cyinfors,int userId) {
        List<ForeCyinfor> list=new ArrayList<>();
        for(Cyinfor cyinfor:cyinfors){
            ForeCyinfor foreCyinfor = new ForeCyinfor();
            User user=userService.get(cyinfor.getUserId());
            foreCyinfor.setHead_img(user.getHeadImg());
            foreCyinfor.setName(user.getName());
            foreCyinfor.setTime(cyinfor.getTime());
            foreCyinfor.setText(cyinfor.getText());
            foreCyinfor.setId(cyinfor.getId());
            foreCyinfor.setUserId(user.getId());
            List<Image>  images=imageService.getByCyid(cyinfor.getId());
            if(null!=images){
                if(1>=images.size()){
                    foreCyinfor.setImage1(images.get(0).getUrl());
                    if(2==images.size()){
                        foreCyinfor.setImage2(images.get(1).getUrl());
                    }
                }
            }
            //判断当前登录的用户是否给这篇点过赞
            LikeInfo likeInfo=likeInfoService.getByCyidAndUserIdAndType(foreCyinfor.getId(),userId,0);
            if(null!=likeInfo){
                foreCyinfor.setIfLike(true);
            }
            list.add(foreCyinfor);
        }
        return list;
    }
}
