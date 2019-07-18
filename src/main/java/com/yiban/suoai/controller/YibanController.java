package com.yiban.suoai.controller;

import cn.yiban.open.Authorize;
import cn.yiban.open.common.User;

import com.alibaba.fastjson.JSON;
import com.yiban.suoai.service.RedisService;
import com.yiban.suoai.util.MapHelper;
import com.yiban.suoai.yiban.AppContext;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import  net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
@Controller
@RequestMapping("")
public class YibanController {


    @Autowired
    RedisService redisService;

    @RequestMapping("/init")
    @ResponseBody
    public void init(HttpServletRequest req, HttpServletResponse res) throws IOException {

        Authorize authorize = new Authorize(AppContext.APP_ID, AppContext.APP_SEC);
        String url = authorize.forwardurl(AppContext.BACK_URL, "QUERY", Authorize.DISPLAY_TAG_T.MOBILE);
        res.sendRedirect(url);
    }

    /**
     * 易班回调
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/back")
    @ResponseBody
    public  Map<String,Object> back(HttpServletRequest req, HttpServletResponse resp){
        Map<String,Object>  map=null;
        String code = req.getParameter(AppContext.KEY_CODE);
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
        //String name = userInfo.getString("yb_usernick");//获取用户名字
       // String sex = userInfo.getString("yb_sex");//获取用户性别*/
       //  System.out.println(yibanUser.me());
       // System.out.println(yibanId);
        redisService.addTokenToRedis(yibanId,AppContext.ACCESS_TOKEN);


        return  MapHelper.success();
       /* User user = new User(AppContext.ACCESS_TOKEN);
        HttpSession session = req.getSession();
        session.setAttribute(AppContext.KEY_USER, user);*/

      //  resp.sendRedirect("http://127.0.0.1:8080/yiban_demo/index.html");

    }
}