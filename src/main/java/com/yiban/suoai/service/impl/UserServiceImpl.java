package com.yiban.suoai.service.impl;

import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.forepojo.ForeRankUser;
import com.yiban.suoai.forepojo.ForeUser;
import com.yiban.suoai.mapper.UserMapper;
import com.yiban.suoai.pojo.Academy;
import com.yiban.suoai.pojo.School;
import com.yiban.suoai.pojo.User;
import com.yiban.suoai.pojo.UserExample;
import com.yiban.suoai.service.AcademyService;
import com.yiban.suoai.service.SchoolService;
import com.yiban.suoai.service.TitleService;
import com.yiban.suoai.service.UserService;

import com.yiban.suoai.util.ErrorCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.NamedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    SchoolService schoolService;
    @Autowired
    AcademyService academyService;
    @Autowired
    TitleService titleService;

    @Override
    public User selectByNameNum(String name, String num) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameEqualTo(name).andStuNumEqualTo(num);
        return userMapper.selectByExample(userExample).get(0);
    }

    @Override
    public int addExperience(int userId, int count) {
        User user=userMapper.selectByPrimaryKey(userId);
        int experience=user.getExperience();
        int nowExperience=experience+count;
        user.setExperience(nowExperience);//记得后面要更新数据库
        int upgradeExperience=1;//所需要的升级经验
        int upgrade=0;//用户是否升级  1为升级了
        for(int i=1;i<=10;i++){
            if(experience<upgradeExperience&&nowExperience>=upgradeExperience){
                //如果用户升级了
                upgrade=1;
                user.setLevel(user.getLevel()+1);//升级
                break;
            }
            upgradeExperience*=2;
        }
        update(user);
        return upgrade;
    }

    @Override
    public void add(User user) {
        userMapper.insert(user);
    }


    @Override
    public void update(User users) {
        userMapper.updateByPrimaryKeySelective(users);
    }

    @Override
    public User get(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ForeRankUser> topTen() throws SAException {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIsRankEqualTo(true).andViolatorEqualTo(false);
        userExample.setOrderByClause("express_time desc,experience desc");
        List<User> users = userMapper.selectByExample(userExample);
        if(users.isEmpty()){
            throw new SAException(ErrorCode.VALUE_IS_EMPTY);
        }else if (users.size() <= 10) {
                List<ForeRankUser> foreRankUsers = new ArrayList<>();
                for (User o : users) {
                    ForeRankUser foreRankUser = new ForeRankUser();
                    foreRankUser.setUserId(o.getId());
                    foreRankUser.setSex(o.getSex());
                    foreRankUser.setArea(o.getArea());
                    foreRankUser.setBackGround(o.getBgImg());
                    foreRankUser.setExperience(o.getExperience());
                    foreRankUser.setHeadImg(o.getHeadImg());
                    foreRankUser.setLevel(o.getLevel());
                    foreRankUser.setSignature(o.getSignature());
                    foreRankUser.setName(o.getName());
                    foreRankUser.setTitle(titleService.get(o.getTitleId()).getName());
                    foreRankUser.setExpress_time(o.getExpressTime());
                    if(o.getSchoolId()!=null) {
                        School school = schoolService.get(o.getSchoolId());
                        foreRankUser.setSchool(school.getName());
                    }
                    if(o.getAcademyId()!=null) {
                        Academy academy = academyService.get(o.getAcademyId());
                        foreRankUser.setAcademy(academy.getName());
                    }
                    foreRankUsers.add(foreRankUser);
                }
            return foreRankUsers;
            }else {
            List<ForeRankUser> foreRankUsers = new ArrayList<>();
            for(int i = 0 ; i < 9;i++){
                    ForeRankUser foreRankUser = new ForeRankUser();
                    foreRankUser.setUserId(users.get(i).getId());
                    foreRankUser.setSex(users.get(i).getSex());
                    foreRankUser.setArea(users.get(i).getArea());
                    foreRankUser.setBackGround(users.get(i).getBgImg());
                    foreRankUser.setExperience(users.get(i).getExperience());
                    foreRankUser.setHeadImg(users.get(i).getHeadImg());
                    foreRankUser.setLevel(users.get(i).getLevel());
                    foreRankUser.setSignature(users.get(i).getSignature());
                    foreRankUser.setName(users.get(i).getName());
                    foreRankUser.setTitle(titleService.get(users.get(i).getTitleId()).getName());
                    foreRankUser.setExpress_time(users.get(i).getExpressTime());
                    if(users.get(i).getSchoolId()!=null) {
                        School school = schoolService.get(users.get(i).getSchoolId());
                        foreRankUser.setSchool(school.getName());
                    }
                    if(users.get(i).getAcademyId()!=null) {
                        Academy academy = academyService.get(users.get(i).getAcademyId());
                        foreRankUser.setAcademy(academy.getName());
                    }
                    foreRankUsers.add(foreRankUser);
                }
            return foreRankUsers;
            }

    }

    @Override
    public List<ForeUser> search(String name) {
        UserExample userExample = new UserExample();
        if(StringUtils.isNotBlank(name)) {
            userExample.createCriteria().andNameLike("%" + name + "%");
        }
        List<User> users = userMapper.selectByExample(userExample);
        List<ForeUser> foreUsers = new ArrayList<>();
        for(User user : users){
            ForeUser foreUser = new ForeUser();
            foreUser.setUserId(user.getId());
            foreUser.setName(user.getName());
            foreUser.setHeadImg(user.getHeadImg());
            foreUser.setBackGround(user.getBgImg());
            foreUser.setTitle(titleService.get(user.getTitleId()).getName());
            foreUser.setArea(user.getArea());
            foreUser.setSignature(user.getSignature());
            foreUser.setlevel(user.getLevel());
            foreUser.setBirthday(user.getBirthday());
            foreUser.setStu_Num(user.getStuNum());
            foreUser.setPaperId(user.getPaper());
            if(user.getSex()==true){
                foreUser.setSex("男");
            }else {
                foreUser.setSex("女");
            }
            if(user.getSchoolId()!=null) {
                School school = schoolService.get(user.getSchoolId());
                foreUser.setSchool(school.getName());
            }
            if(user.getAcademyId()!=null) {
                Academy academy = academyService.get(user.getAcademyId());
                foreUser.setAcademy(academy.getName());
            }
            foreUsers.add(foreUser);
        }
        return foreUsers;
    }
}
