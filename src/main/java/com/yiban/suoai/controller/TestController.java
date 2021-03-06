package com.yiban.suoai.controller;

import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.service.RedisService;
import com.yiban.suoai.util.MapHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Controller
//@RequestMapping("test")
@Api("测试")
public class TestController {



    @Autowired
    RedisService redisService;


    @ApiOperation(value = "登录，仅测试使用", notes = "登录，仅测试使用")
    @RequestMapping(value ="loginTest" , method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateMatching(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("userid")  @ApiParam(value = "用户ID") Integer userid){
        Map map=new HashMap();
        redisService.addTokenToRedis(userid, token);
        return MapHelper.success();
    }


    @ApiOperation(value = "重定向，仅测试使用", notes = "重定向，仅测试使用")
    @RequestMapping(value ="togoTest" , method = RequestMethod.GET)
    public void togoTest(
            HttpServletRequest req, HttpServletResponse res,ModelAndView    model) throws IOException {
        Map map=new HashMap();
        res.sendRedirect("https://www.baidu.com?i=1");
       /* model.addObject("user", 55);
        model.setViewName("https://www.baidu.com");
        return model;*/
       // return "/index";

    }


    @ApiOperation(value = "抛出异常，仅测试使用", notes = "抛出异常，仅测试使用")
    @RequestMapping(value ="exceptionTest" , method = RequestMethod.POST)
    @ResponseBody
    public  Map<String, Object>  exceptionTest(
            HttpServletRequest req, HttpServletResponse res) throws IOException, SAException {
        Map map=new HashMap();
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            synchronized (this){
                String stampString= null;//如果获取不到里面会报错
                try {

                } catch (Exception e ){

                }

                return stampString;
            }

        });
        throw new SAException("eer","1");


    }


}
