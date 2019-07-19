package com.yiban.suoai.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.forepojo.ForeCyinfor;
import com.yiban.suoai.forepojo.ForeReview;
import com.yiban.suoai.pojo.Cyinfor;
import com.yiban.suoai.pojo.Image;
import com.yiban.suoai.pojo.Review;
import com.yiban.suoai.service.CyinforService;
import com.yiban.suoai.service.ImageService;
import com.yiban.suoai.service.RedisService;
import com.yiban.suoai.service.ReviewService;
import com.yiban.suoai.service.impl.ImageServiceImpl;
import com.yiban.suoai.util.FileHelper;
import com.yiban.suoai.util.MapHelper;
import com.yiban.suoai.util.PageUtil;
import com.yiban.suoai.util.UUIDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("square")
@Api("广场")
public class SquareController {

    private static Logger logger = Logger.getLogger(SquareController.class);// 添加日志


    @Autowired
    RedisService redisService;
    @Autowired
    CyinforService cyinforService;
    @Autowired
    ImageService imageService;
    @Autowired
    ReviewService reviewService;


    @ApiOperation(value = "添加表白", notes = "添加表白")
    @RequestMapping(value ="expression" , method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> expression(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam("image")  @ApiParam(value = "用户ID") MultipartFile[] uploadFiles,
            @RequestParam(value = "who",defaultValue = "0")  @ApiParam(value = "who  是否有特定的表白对象，没有传") int who,
            @RequestParam(value = "privacy")  @ApiParam(value = "privacy 1 为私密表白  0为公开表白") Boolean privacy,
            @RequestParam(value = "hide")  @ApiParam(value = "hide 1 为身份隐藏 0 为身份可视") Boolean hide,
            @RequestParam(value = "text")  @ApiParam(value = "内容") String text
    ) throws IOException, SAException {
            Map map = new HashMap();
            int userid= redisService.getUserId(token);
            int hasImage=uploadFiles.length;//图片的数量
            Cyinfor cyinfor= cyinforService.full(userid,privacy,hide,who,hasImage,text);
            int cyid=cyinforService.add(cyinfor);
            for(MultipartFile file : uploadFiles){
                String uuid=UUIDUtil.getUUID();//使用uuid作为图片的名称
               String path= FileHelper.FileSave(file,uuid,FileHelper.cyinfor);
                //保存路径
                Image image=new Image(path,cyid);
                imageService.add(image);
            }
            map= MapHelper.success();
        return map;
    }


    @ApiOperation(value = "获取广场表白内容", notes = "获取广场表白内容")
    @RequestMapping(value ="expression" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> expression(@RequestHeader("token") @ApiParam(value = "权限校验") String token,
                                          @RequestParam(value = "schoolId",defaultValue = "0")  @ApiParam(value = "学校筛选  没有筛选不传") int schoolId ,
                                          @RequestParam(value = "academyId",defaultValue = "0")  @ApiParam(value = "学院筛选  没有筛选不传") int academyId ,
                                          @RequestParam("startPage") @ApiParam(value = "起始页") Integer start){
        PageHelper.offsetPage(start * PageUtil.pageSize,  PageUtil.pageSize);
        List<Cyinfor> cyinfors = cyinforService.getAll();
        List<ForeCyinfor>  list=cyinforService.foreFull(cyinfors);
        int total = (int) new PageInfo<>(list).getTotal();
        Map<String, Object> map = MapHelper.success();
        map.put("data", list);
        map.put("page", PageUtil.getPage(total, list.size(), start));
        return map;
    }



    @ApiOperation(value = "添加表白的评论", notes = "添加表白的评论")
    @RequestMapping(value ="comment" , method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> comment( @RequestHeader("token") @ApiParam(value = "权限校验") String token,
                                        @RequestParam(value = "cyid")  @ApiParam(value = "表白的id") int cyid,
                                        @RequestParam(value = "text")  @ApiParam(value = "评论内容") String text,
                                        @RequestParam(value = "replyId",defaultValue = "0")  @ApiParam(value = "评论的id  如果不是评论的评论就不用传") int replyId) throws SAException {
        Map map = new HashMap();
        int userId= redisService.getUserId(token);
        Review review= reviewService.full(cyid,userId,text,replyId);
        reviewService.add(review);
        map=MapHelper.success();
        return map;
    }

    @ApiOperation(value = "获取表白的评论", notes = "获取表白的评论")
    @RequestMapping(value ="comment" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> comment( @RequestHeader("token") @ApiParam(value = "权限校验") String token,
                                        @RequestParam(value = "cyid")  @ApiParam(value = "表白的id") int cyid){
            Map map=MapHelper.success();
            List<Review> reviews=reviewService.getAllButReply(cyid);
            List<ForeReview> list=reviewService.foreFull(reviews);
            map.put("date",list);
            return map;
    }

   /* @ApiOperation(value = "获取表白的评论的", notes = "获取表白的评论")
    @RequestMapping(value ="comment" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> comment( @RequestHeader("token") @ApiParam(value = "权限校验") String token,
                                        @RequestParam(value = "cyid")  @ApiParam(value = "表白的id") int cyid){
        Map map=MapHelper.success();
        List<Review> reviews=reviewService.getAllButReply(cyid);
        List<ForeReview> list=reviewService.foreFull(reviews);
        map.put("date",list);
        return map;
    }*/

   /* @ApiOperation(value = "获取表白评论的评论", notes = "获取表白评论的评论")
    @RequestMapping(value ="comment" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> commentofcomment( @RequestHeader("token") @ApiParam(value = "权限校验") String token,
                                                 @RequestParam(value = "reviewId")  @ApiParam(value = "评论的id") int reviewId){

    }*/


}
