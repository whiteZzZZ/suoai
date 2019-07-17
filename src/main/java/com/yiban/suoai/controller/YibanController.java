package com.yiban.suoai.controller;

import cn.yiban.open.Authorize;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yiban.suoai.yiban.AppContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("")
public class YibanController {


    @RequestMapping("/init")
    @ResponseBody
    public void init(HttpServletRequest req, HttpServletResponse res) throws IOException {

        Authorize authorize = new Authorize(AppContext.APP_ID, AppContext.APP_SEC);
        String url = authorize.forwardurl(AppContext.BACK_URL, "QUERY", Authorize.DISPLAY_TAG_T.MOBILE);
        res.sendRedirect(url);
    }


    @RequestMapping("/back")
    @ResponseBody
    public void back(HttpServletRequest req, HttpServletResponse resp){
        String code = req.getParameter(AppContext.KEY_CODE);
        if (code == null || code.equals("")) {
            //resp.sendRedirect("/yiban_demo/index.html");
            System.out.println("登录失败");
        }
        System.out.println(code);

        Authorize authorize = new Authorize(AppContext.APP_ID, AppContext.APP_SEC);
        String text = authorize.querytoken(code, AppContext.BACK_URL);

        System.out.println(text);
        JSONObject json = JSON.parseObject(text);
        AppContext.ACCESS_TOKEN = json.getString(AppContext.KEY_TOKEN);

       /* User user = new User(AppContext.ACCESS_TOKEN);
        HttpSession session = req.getSession();
        session.setAttribute(AppContext.KEY_USER, user);*/

      //  resp.sendRedirect("http://127.0.0.1:8080/yiban_demo/index.html");

    }
}
