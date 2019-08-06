package com.yiban.suoai.controller;

import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.pojo.Tip;
import com.yiban.suoai.pojo.TipBank;
import com.yiban.suoai.service.TipService;
import com.yiban.suoai.util.ErrorCode;
import com.yiban.suoai.util.MapHelper;
import com.yiban.suoai.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("tip")
public class TipController {

    @Autowired
    TipService tipService;
    @Autowired
    RedisUtil redisUtil;

    @RequestMapping("addTip")
    public Map<String,Object> addTip(@RequestParam("source")Integer source,
                                     @RequestParam("sourceId")Integer sourceId,
                                     @RequestParam("type")Integer type,
                                     @RequestParam("content")String content){
        Tip t = new Tip();
        t.setSource(source);
        t.setSourceId(sourceId);
        t.setContent(content);
        t.setStatus(0);  //未审核状态
        t.setType(type);
        if(tipService.addTip(t)>0)return MapHelper.success();
        else return MapHelper.error();
    }

//    @RequestMapping("deleteTip")
//    public Map<String,Object> deleteTip(@RequestParam("id")Integer id){
//        if(tipService.deleteTip(id)>0)return MapHelper.success();
//        else return MapHelper.error();
//    }

    @RequestMapping("checkTip")
    public Map<String,Object> checkTip(@RequestParam("tipList")List<TipBank> list,
                                       @RequestHeader("token")String token){
        try {
            Map<String,Object> map =MapHelper.success();
            int userId = redisUtil.getUserId(token);
            int flag = tipService.checkTip(userId,list);
            if(flag!=-1){
                map.put("result",1);
                return map;
            } else {
                map.put("result",0);
                return map;
            }
        } catch (SAException e) {
            e.printStackTrace();
            return MapHelper.error(e.getCode().getMsg());
        }
    }

    @RequestMapping("getExam")
    public Map<String,Object> getExam(@RequestHeader("token")String token){
        try {
            int userId = redisUtil.getUserId(token);
            List<Tip> list = tipService.getExam(userId);
            Map<String,Object> map = MapHelper.success();
            map.put("exam",list);
            return map;
        } catch (SAException e) {
            e.printStackTrace();
            return MapHelper.error(e.getCode().getMsg());
        }
    }
}
