package com.yiban.suoai.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.forepojo.ForeWordReview;
import com.yiban.suoai.pojo.WordReview;
import com.yiban.suoai.service.RedisService;
import com.yiban.suoai.service.WeekWordService;
import com.yiban.suoai.service.WordReviewService;
import com.yiban.suoai.util.MapHelper;
import com.yiban.suoai.util.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("weekWord")
@Api(value = "每周一话")
public class WeekWordController {

    private static Logger logger = Logger.getLogger(WeekWordController.class);// 添加日志

    @Autowired
    WeekWordService weekWordService;
    @Autowired
    RedisService redisService;
    @Autowired
    WordReviewService wordReviewService;

    @ApiOperation(value = "获取每周一话",notes = "获取每周一话")
    @RequestMapping(value = "head",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getHead(
            @RequestHeader(value = "token") @ApiParam(value = "验证") String token
            ){
        Map<String,Object> map = MapHelper.success();
        map.put("data",weekWordService.getByWeek());
        return map;
    }

    @ApiOperation(value = "获取评论",notes = "获取评论")
    @RequestMapping(value = "content",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getWeekWord1(
            @RequestHeader(value = "token") @ApiParam(value = "验证") String token,
            @RequestParam(value = "weekWordId")  @ApiParam(value = "每周一话的id") int weekWordId,
            @RequestParam("startPage") @ApiParam(value = "起始页") Integer start
    ) throws SAException {
        int userId=redisService.getUserId(token);
        Map map=MapHelper.success();
        PageHelper.offsetPage(start * PageUtil.pageSize,  PageUtil.pageSize);

        List<WordReview> all = wordReviewService.getAll(weekWordId);
        int total = (int) new PageInfo<>(all).getTotal();
        List<ForeWordReview> foreWordReviews = wordReviewService.foreFull(all, userId);

        map.put("date",foreWordReviews);
        map.put("page", PageUtil.getPage(total, all.size(), start));
        return map;
    }



    @ApiOperation(value = "发表评论",notes = "获取评论")
    @RequestMapping(value = "content",method = RequestMethod.PUT)
    @ResponseBody
    public Map<String,Object> getWeekWord2(
            @RequestHeader(value = "token") @ApiParam(value = "验证") String token,
            @RequestParam(value = "weekWordId") @ApiParam(value = "每周一话Id") int weekWordId,
            @RequestParam(value = "paperId") @ApiParam(value = "信纸Id") int paperId,
            @RequestParam(value = "text") @ApiParam(value = "内容") String text) throws SAException {

        int userId= redisService.getUserId(token);
        WordReview wordReview = wordReviewService.full(weekWordId, text, userId, paperId);
        wordReviewService.add(wordReview);

        return MapHelper.success();
    }


}
