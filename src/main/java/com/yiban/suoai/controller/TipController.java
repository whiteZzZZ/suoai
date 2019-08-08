package com.yiban.suoai.controller;

import com.alibaba.fastjson.JSONObject;
import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.pojo.Tip;
import com.yiban.suoai.pojo.TipBank;
import com.yiban.suoai.service.TipService;
import com.yiban.suoai.util.ErrorCode;
import com.yiban.suoai.util.MapHelper;
import com.yiban.suoai.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("tip")
@Api("举报")
public class TipController {

    @Autowired
    TipService tipService;
    @Autowired
    RedisUtil redisUtil;
    @ApiOperation(value = "添加一条举报", notes = "添加一条举报")
    @RequestMapping("addTip")
    public Map<String,Object> addTip( @ApiParam(value = "资源类型 1传阅 2评论 3每日一话评论")@RequestParam("source")Integer source,
                                      @ApiParam(value = "资源id")@RequestParam("sourceId")Integer sourceId,
                                      @ApiParam(value = "举报类型")@RequestParam("type")Integer type,
                                      @ApiParam(value = "举报内容")@RequestParam("content")String content){
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
    @ApiOperation(value = "检查考试答案", notes = "检查考试答案")
    @RequestMapping("checkTip")
    public Map<String,Object> checkTip(@ApiParam(value = "考试答案json，举例{\n" +
            "   \"tipList\":[{\n" +
            "                \"id\": 1,\n" +
            "                \"source\": 1,\n" +
            "                \"sourceId\": 104,\n" +
            "                \"type\": 1,\n" +
            "                \"status\": 1,\n" +
            "                \"content\": \"tiptest\",\n" +
            "                \"ans\": true\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 2,\n" +
            "                \"source\": 1,\n" +
            "                \"sourceId\": 104,\n" +
            "                \"type\": 1,\n" +
            "                \"status\": 1,\n" +
            "                \"content\": \"tiptest\",\n" +
            "                \"ans\":true\n" +
            "            }]}")@RequestBody JSONObject js,
                                       @ApiParam(value = "token")@RequestHeader("token")String token){
        try {
            List<TipBank> list = js.getJSONArray("tipList").toJavaList(TipBank.class);
            list.forEach(n-> System.out.println(n.getAns()));
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
    @ApiOperation(value = "获取考试", notes = "获取考试")
    @RequestMapping("getExam")
    public Map<String,Object> getExam(@ApiParam(value = "token")@RequestHeader("token")String token){
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
