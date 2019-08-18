package com.yiban.suoai.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.forepojo.ForeCyinfor;
import com.yiban.suoai.forepojo.ForeRankUser;
import com.yiban.suoai.forepojo.ForeReview;
import com.yiban.suoai.pojo.*;
import com.yiban.suoai.service.*;
import com.yiban.suoai.service.impl.ImageServiceImpl;
import com.yiban.suoai.service.impl.RedisServiceImpl;
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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

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
    @Autowired
    LikeInfoService likeInfoService;
    @Autowired
    WordReviewService wordReviewService;
    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;
    @Autowired
    WallService wallService;

    @ApiOperation(value = "添加表白", notes = "添加表白  如果有图片 用返回的userid再调用  文件图片上传的接口")
    @RequestMapping(value ="expression" , method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> expressionPut(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token,
            @RequestParam(value = "who",defaultValue = "0")  @ApiParam(value = "who  是否有特定的表白对象 即特定表白对象的userid，没有传") int who,
            @RequestParam(value = "privacy")  @ApiParam(value = "privacy 1 为私密表白  0为公开表白") Boolean privacy,
            @RequestParam(value = "hide")  @ApiParam(value = "hide 1 为身份隐藏 0 为身份可视") Boolean hide,
            @RequestParam(value = "text")  @ApiParam(value = "内容") String text
    ) throws IOException, SAException {
            Map map = new HashMap();
            int userid= redisService.getUserId(token);
            //   int hasImage=uploadFiles.length;//图片的数量
            Cyinfor cyinfor= cyinforService.full(userid,privacy,hide,who,0,text);
            int cyid=cyinforService.add(cyinfor);
            /*for(MultipartFile file : uploadFiles){
                String uuid=UUIDUtil.getUUID();//使用uuid作为图片的名称
               String path= FileHelper.FileSave(file,uuid,FileHelper.cyinfor);
                //保存路径
                Image image=new Image(path,cyid);
                imageService.add(image);
            }*/
            //如果有特定的表白对象  则创建消息
            if(0!=who){
                //让对方被表白的次数 +1
                User user=userService.get(who);
                int expressTime=user.getExpressTime();
                expressTime++;
                user.setExpressTime(expressTime);
                userService.update(user);
                //加入 message
                Message message=new Message();
                if(true==privacy){
                    //如果是私密表白
                    message.setType(1);
                }else {
                    message.setType(0);//公开表白
                }
                message.setCyId(cyid);
                if(true==hide){
                    //如果要隐藏身份
                    message.setSponsorId(0);
                }else {
                    message.setSponsorId(userid);
                }
                message.setUserId(who);//收到消息的用户
                message.setTime(new Date());
                messageService.add(message);

                //创建消息后 还有让通知对方  加入redis
                redisService.addImformToRedis(who, RedisServiceImpl.Expression);//对方的userId

            }


            map= MapHelper.success();
            map.put("cyid",cyid);
        return map;
    }

    /**
     * 进入程序后 调用的第一个接口，  要判断用户没有违规，如果有违规 要跳转到 志愿服务
     * @param token
     * @param schoolId
     * @param academyId
     * @param start
     * @return
     * @throws SAException
     */
    @ApiOperation(value = "获取广场表白内容", notes = "获取广场表白内容  原图在缩略图的名称基础上加-y")
    @RequestMapping(value ="expression" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> expressionGet(@RequestHeader("token") @ApiParam(value = "权限校验") String token,
                                          @RequestParam(value = "schoolId",defaultValue = "0")  @ApiParam(value = "学校筛选  没有筛选不传") int schoolId ,
                                          @RequestParam(value = "academyId",defaultValue = "0")  @ApiParam(value = "学院筛选  没有筛选不传") int academyId ,
                                          @RequestParam("startPage") @ApiParam(value = "起始页") Integer start) throws SAException {

        int userId=redisService.getUserId(token);
        PageHelper.offsetPage(start * PageUtil.pageSize,  PageUtil.pageSize);
        List<Cyinfor> cyinfors = cyinforService.getAll();
        int total = (int) new PageInfo<>(cyinfors).getTotal();
        List<ForeCyinfor>  list=cyinforService.foreFull(cyinfors,userId);//里面方法有 对每个表白判断  当前的这个用户是不是点了赞的  也有图片的路径

        User user=userService.get(userId);
        Map<String, Object> map = MapHelper.success();
        map.put("violator",user.getViolator());
        map.put("data", list);
        map.put("page", PageUtil.getPage(total, cyinfors.size(), start));
        return map;
    }




    @ApiOperation(value = "获取单个表白内容", notes = "获取表白内容  原图在缩略图的名称基础上加")
    @RequestMapping(value ="oneExpression" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> oneExpression(@RequestHeader("token") @ApiParam(value = "权限校验") String token,
                                             @RequestParam(value = "cyid")  @ApiParam(value = "cyid") int cyid ) throws SAException {

        int userId=redisService.getUserId(token);
        Cyinfor cyinfor=cyinforService.get(cyid);
        List<Cyinfor> cyinfors = new ArrayList<>();
        cyinfors.add(cyinfor);
        List<ForeCyinfor>  list=cyinforService.foreFull(cyinfors,userId);//里面方法有 对每个表白判断  当前的这个用户是不是点了赞的  也有图片的路径


        Map<String, Object> map = MapHelper.success();
        if(list.isEmpty()||null==list){
            return MapHelper.error("无法找到该cyid");
        }
        map.put("data", list.get(0));
        return map;
    }



    @ApiOperation(value = "添加表白的评论", notes = "添加表白的评论")
    @RequestMapping(value ="comment" , method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> commentPut( @RequestHeader("token") @ApiParam(value = "权限校验") String token,
                                        @RequestParam(value = "cyid")  @ApiParam(value = "表白的id") int cyid,
                                        @RequestParam(value = "text")  @ApiParam(value = "评论内容") String text,
                                        @RequestParam(value = "replyId",defaultValue = "0")  @ApiParam(value = "评论的id  如果不是评论的评论就不用传") int replyId) throws SAException {
        Map map = new HashMap();
        int userId= redisService.getUserId(token);
        Review review= reviewService.full(cyid,userId,text,replyId);
        reviewService.add(review);

        //给对方通知
        Cyinfor cyinfor=cyinforService.get(cyid);
        cyinfor.setReviewTime(cyinfor.getReviewTime()+1);//增加评论数量
        cyinforService.update(cyinfor);
        int imformUserId=cyinfor.getUserId();//被通知的userId
        //创建消息
        Message message=new Message();
        message.setType(3);//评论
        message.setCyId(review.getId());
        message.setSponsorId(userId);//发起者
        message.setUserId(imformUserId);
        message.setTime(new Date());
        messageService.add(message);

        //redis通知他
        redisService.addImformToRedis(cyinfor.getUserId(), RedisServiceImpl.Comment);//对方的userId




       // userService.addExperience(userId,1);
        map=MapHelper.success();
        return map;
    }

    @ApiOperation(value = "获取表白的评论", notes = "获取表白的评论")
    @RequestMapping(value ="comment" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> commentGet( @RequestHeader("token") @ApiParam(value = "权限校验") String token,
                                        @RequestParam(value = "cyid")  @ApiParam(value = "表白的id") int cyid,
                                           @RequestParam("startPage") @ApiParam(value = "起始页") Integer start
    ) throws SAException {
        int userId=redisService.getUserId(token);
            Map map=MapHelper.success();
            PageHelper.offsetPage(start * PageUtil.pageSize,  PageUtil.pageSize);
            List<Review> reviews=reviewService.getAllButReply(cyid);
            int total = (int) new PageInfo<>(reviews).getTotal();
            List<ForeReview> list=reviewService.foreFull(reviews,userId);
            map.put("date",list);
            map.put("page", PageUtil.getPage(total, reviews.size(), start));
            return map;
    }

    @ApiOperation(value = "获取表白的评论的评论", notes = "获取表白的评论的评论")
    @RequestMapping(value ="commentofcomment" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> commentofcommentGet( @RequestHeader("token") @ApiParam(value = "权限校验") String token,
                                        @RequestParam(value = "cyid")  @ApiParam(value = "表白的id") int cyid,
                                        @RequestParam(value = "reviewId")  @ApiParam(value = "评论的id") int reviewId,
                                                    @RequestParam("startPage") @ApiParam(value = "起始页") Integer start) throws SAException {
        Map map=MapHelper.success();
        int userId=redisService.getUserId(token);

        //先查找所有评论中的回复评论的id是  这条的评论id
        List<ForeReview> foreReviews=new ArrayList<>();
        List<Review> reviews=reviewService.getAllByReplyId(reviewId);//二、三级评论
        if(null==reviews){
            //没有三级评论
            return map;
        }
        int threeIndex=reviews.size();//三级评论的起始位置
        List<ForeReview> subForeReview1=reviewService.foreFullSecondaryComments(reviews,userId);//二级评论 转前端需要的格式
        //通过二级评论获得三级评论
        List<Review>  threeReview=new ArrayList<>();//三级评论
        //for(Review review:reviews){
        int size=reviews.size();
        for(int i=0;i<size;i++){
            int replyId=reviews.get(i).getReplyId();
             if(0!=replyId){
                List<Review> reviews2=reviewService.getAllByReplyId(reviews.get(i).getId());
                if(null!=reviews2){
                    reviews.addAll(reviews2);
                    size+=reviews2.size();
                }
            }
        }

        //对三级评论进行转成前端需要的格式，记得三级评论要显示回复方的昵称
        List<Review> reviews3=reviews.subList(threeIndex,size);//三级评论
        List<ForeReview> foreReviews3=reviewService.foreFull(reviews3,userId);//对三级评论 进行格式化


        List<ForeReview> list=new ArrayList<>();//把格式化好的二三级评论加进去
        list.addAll(subForeReview1);
        list.addAll(foreReviews3);
        Collections.sort(list, new Comparator<ForeReview>() {
            @Override
            public int compare(ForeReview o1, ForeReview o2) {
                return o2.getReviewId().compareTo(o1.getReviewId());
            }
        });
        map.put("date",list);
        return map;
    }

    @ApiOperation(value = "给表白或者评论点赞", notes = "给表白或者评论点赞")
    @RequestMapping(value ="givelike" , method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> givelike( @RequestHeader("token") @ApiParam(value = "权限校验") String token,
                                         @RequestParam(value = "cyid",defaultValue = "0")  @ApiParam(value = "表白的id 如果不是表白就不传  下面一样" ) int cyid,
                                         @RequestParam(value = "reviewId",defaultValue = "0")  @ApiParam(value = "评论的id") int reviewId ,
                                         @RequestParam(value = "wordReviewId",defaultValue = "0")  @ApiParam(value = "每周一话的评论id") int wordReviewId) throws SAException {
        Map map=MapHelper.success();
        int type=0;
        LikeInfo likeInfo=null;
        int id=0;
        int imformUserId=0;
        int userId=redisService.getUserId(token);

        if(0!=cyid){
            type=6;
            likeInfo= likeInfoService.getByCyidAndUserIdAndType(cyid,userId,type);
            id=cyid;
        }else if(0!=reviewId){
            type=7;
            likeInfo= likeInfoService.getByCyidAndUserIdAndType(reviewId,userId,type);
            id=reviewId;
        }else {
            type=8;
            likeInfo= likeInfoService.getByCyidAndUserIdAndType(wordReviewId,userId,type);
            id=wordReviewId;
        }
        //如果已经点赞了，就删掉
       if(null!=likeInfo){
           likeInfoService.delete(likeInfo.getId());
           //点赞数量减一
           if(type==6){
                Cyinfor cyinfor=cyinforService.get(cyid);
                  imformUserId=cyinfor.getUserId();
                int likeCount=cyinfor.getLikeTime()-1;
                cyinfor.setLikeTime(likeCount);
                cyinforService.update(cyinfor);
           }else if(type==7){
                Review review=reviewService.get(reviewId);
               imformUserId=review.getUserId();
                int likeCount=review.getLikeTime()-1;
                review.setLikeTime(likeCount);
                reviewService.update(review);
           }else {
               WordReview wordReview=wordReviewService.get(wordReviewId);
               imformUserId=wordReview.getUserId();
               int likeCount=wordReview.getLikeTime()-1;
               wordReview.setLikeTime(likeCount);
               wordReviewService.update(wordReview);
           }

           //删除消息
            messageService.deleteByAll(type,cyid,imformUserId,userId);
           redisService.deleteOneImformFromRedis(imformUserId, RedisServiceImpl.Like);


       }else {


           //没有就加入
           likeInfoService.add(new LikeInfo(id,userId,(byte)type));
           //点赞数量加一
           if(type==6){
               Cyinfor cyinfor=cyinforService.get(cyid);
               imformUserId=cyinfor.getUserId();
               int likeCount=cyinfor.getLikeTime()+1;
               cyinfor.setLikeTime(likeCount);
               cyinforService.update(cyinfor);
           }else if(type==7){
               Review review=reviewService.get(reviewId);
               imformUserId=review.getUserId();
               int likeCount=review.getLikeTime()+1;
               review.setLikeTime(likeCount);
               reviewService.update(review);
           }else {
               WordReview wordReview=wordReviewService.get(wordReviewId);
               imformUserId=wordReview.getUserId();
               int likeCount=wordReview.getLikeTime()+1;
               wordReview.setLikeTime(likeCount);
               wordReviewService.update(wordReview);
           }

           //创建消息
           Message message=new Message();
           message.setType(type);//评论
           message.setCyId(id);
           message.setSponsorId(userId);//发起者
           message.setUserId(imformUserId);//接到通知的人
           message.setTime(new Date());
           messageService.add(message);

           //redis通知他
           redisService.addImformToRedis(imformUserId, RedisServiceImpl.Like);//对方的userId


       }
        return map;
    }


   /* *//**
     * 从数据库拿实在太慢了  要从redis
     * @param token
     * @return
     * @throws SAException
     *//*
    @ApiOperation(value = "获取表白墙", notes = "获取表白墙")
    @RequestMapping(value ="getWall" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getWall( @RequestHeader("token") @ApiParam(value = "权限校验") String token) throws SAException {
        int userId=redisService.getUserId(token);
        List<Cyinfor> cyinfors=wallService.get();
        List<ForeCyinfor>  list=cyinforService.foreFull(cyinfors,userId);
        //对每个表白判断  当前的这个用户是不是点了赞的
        Map<String, Object> map = MapHelper.success();
        map.put("data", list);
        return map;
    }*/

    /**
     * 本来要从redis拿到的  但是  从redis 拿 如果用户点赞后  redis是 0点时拿到的  不能更新  顾从数据库直接去好了
     * @param token
     * @return
     * @throws SAException
     */
    @ApiOperation(value = "获取表白墙", notes = "获取表白墙")
    @RequestMapping(value ="getWall" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getWall( @RequestHeader("token") @ApiParam(value = "权限校验") String token) throws SAException {
        int userId=redisService.getUserId(token);
        List<Cyinfor> cyinfors=cyinforService.topTen();
        List<ForeCyinfor>  list=cyinforService.foreFull(cyinfors,userId);
        //对每个表白判断  当前的这个用户是不是点了赞的
        Map<String, Object> map = MapHelper.success();
        map.put("data", list);
        return map;
    }

    @ApiOperation(value = "获取排行榜", notes = "获取排行榜")
    @RequestMapping(value ="getRanking" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getRanking(
            @RequestHeader("token") @ApiParam(value = "权限校验") String token
    ) throws SAException {
        List<ForeRankUser> foreRankUsers = userService.topTen();
        Map<String, Object> map = MapHelper.success();
        map.put("data", foreRankUsers);
        return map;
    }



}
