package com.yiban.suoai.controller;

import cn.yiban.open.Authorize;
import cn.yiban.open.common.User;

import com.alibaba.fastjson.JSON;
import com.yiban.suoai.pojo.Image;
import com.yiban.suoai.pojo.School;
import com.yiban.suoai.service.ImageService;
import com.yiban.suoai.service.RedisService;
import com.yiban.suoai.service.SchoolService;
import com.yiban.suoai.service.UserService;
import com.yiban.suoai.util.FileHelper;
import com.yiban.suoai.util.MapHelper;
import com.yiban.suoai.util.UUIDUtil;
import com.yiban.suoai.yiban.AppContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import  net.sf.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("")
public class YibanController {

    private static Logger logger = Logger.getLogger(YibanController.class);// 添加日志


    @Autowired
    RedisService redisService;
    @Autowired
    UserService userService;
    @Autowired
    SchoolService schoolService;

    @RequestMapping(value = "/init")
    @ApiOperation(value = "易班登录入口",notes = "易班登录入口")
    @ResponseBody
    public void init(HttpServletRequest req, HttpServletResponse res) throws IOException {

        logger.error("进入init方法");
        Authorize authorize = new Authorize(AppContext.APP_ID, AppContext.APP_SEC);
        String url = authorize.forwardurl(AppContext.BACK_URL, "QUERY", Authorize.DISPLAY_TAG_T.WEB);
        System.out.println("url="+url);
        logger.error("url="+url);
        res.sendRedirect(url);
    }

    /**
     * 易班回调
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/back")
    @ApiOperation(value = "易班回调地址",notes = "易班回调地址")
    @ResponseBody
    public  Map<String,Object> back(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.error("调用成功");
        Map<String,Object>  map=null;
//        System.out.println(AppContext.KEY_CODE);
        String code = req.getParameter(AppContext.KEY_CODE);
//        System.out.println(code);
        if (code == null || code.equals("")) {
            //resp.sendRedirect("/yiban_demo/index.html");
            System.out.println("登录失败");
            return MapHelper.error();
        }
        //System.out.println(code);

        Authorize authorize = new Authorize(AppContext.APP_ID, AppContext.APP_SEC);
        String text = authorize.querytoken(code, AppContext.BACK_URL);

       // System.out.println(text);
        com.alibaba.fastjson.JSONObject json = JSON.parseObject(text);
        AppContext.ACCESS_TOKEN = json.getString(AppContext.KEY_TOKEN);
        User yibanUser = new User(AppContext.ACCESS_TOKEN);
        JSONObject userInfo = JSONObject.fromObject(yibanUser.me()).getJSONObject("info");

        int yibanId = userInfo.getInt("yb_userid");//获取用户id
        com.yiban.suoai.pojo.User user = userService.get(yibanId);



        if(user == null){
            String name = userInfo.getString("yb_usernick");//获取用户昵称
            String sex = userInfo.getString("yb_sex");//获取用户性别
            String school = userInfo.getString("yb_schoolname");
            String trueName = userInfo.getString("yb_username");//获取用户名
            String headImg = userInfo.getString("yb_userhead");//获取用户易班头像

            com.yiban.suoai.pojo.User user1 = new com.yiban.suoai.pojo.User();
            user1.setId(yibanId);
            user1.setName(name);
            user1.setTurename(trueName);
            user1.setHeadImg(headImg);
            if(sex=="男") {
                user1.setSex(true);
            }else {
                user1.setSex(false);
            }
            List<School> schools = schoolService.selectByName(school);
            if(schools!=null) {
                for (School o : schools) {
                    if (o.getName().equals(school)) {
                        user1.setSchoolId(o.getId());
                        break;
                    }
                }
            }else {
                School school1 = new School();
                school1.setName(school);
                schoolService.add(school1);
            }
            redisService.addTokenToRedis(yibanId,AppContext.ACCESS_TOKEN);
            userService.add(user1);
            map=MapHelper.success();
            map.put("userId",yibanId);
            map.put("token",AppContext.ACCESS_TOKEN);
            return  map;
        }else {

            redisService.addTokenToRedis(yibanId,AppContext.ACCESS_TOKEN);
            map=MapHelper.success();
            map.put("userId",yibanId);
            map.put("token",AppContext.ACCESS_TOKEN);
            return  map;
        }

//        byte file = (byte) userInfo.get("yb_userhead");//获取头像
//        String uuid= UUIDUtil.getUUID();//使用uuid作为图片的名称
//        String path= FileHelper.FileSave(file,uuid,FileHelper.headImg);//头像路径



       /* User user = new User(AppContext.ACCESS_TOKEN);
        HttpSession session = req.getSession();
        session.setAttribute(AppContext.KEY_USER, user);*/

      //  resp.sendRedirect("http://127.0.0.1:8080/yiban_demo/index.html");

    }
}
