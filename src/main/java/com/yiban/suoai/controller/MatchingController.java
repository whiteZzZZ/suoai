package com.yiban.suoai.controller;


import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.pojo.LikeInfo;
import com.yiban.suoai.pojo.Message;
import com.yiban.suoai.pojo.Review;
import com.yiban.suoai.pojo.User;
import com.yiban.suoai.service.*;
import com.yiban.suoai.service.impl.RedisServiceImpl;
import com.yiban.suoai.util.MapHelper;
import com.yiban.suoai.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Controller
@RequestMapping("matching")
@Api("匹配")
public class MatchingController {

    private static Logger logger = Logger.getLogger(MatchingController.class);// 添加日志


    @Autowired
    RedisService redisService;
    @Autowired
    UserService userService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    LikeInfoService likeInfoService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    MessageService messageService;

  /*  *//**
     * 思路： 先从redis中 获取有没有异性  如果没有再把自己加入匹配队列
     * 男女都各加锁  男女队列里最多一人  考虑阻塞超过多少秒自动退出
     * @param token
     * @return
     * @throws SAException
     * @throws InterruptedException
     *//*
    @ApiOperation(value = "普通匹配", notes = "普通匹配")
    @RequestMapping(value ="ordinaryMatch" , method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> ordinaryMatch(@RequestHeader("token") @ApiParam(value = "权限校验") String token) throws SAException, InterruptedException {


        Map<String, Object> map=null;
         int blockTime=60000;//等待六十秒
          int userId=redisService.getUserId(token);
          int matchUserId=0;//获得匹配成功的userId
          User user=userService.get(userId);
          redisService.addOrdinaryMatch(userId,user.getSex()?1:0);
          int oppositeSex=user.getSex()?0:1;
          //String result1=redisService.getOrdinaryMatch(oppositeSex);
          String result2=null;


          if(oppositeSex==1){
              //最多等待60秒
             for(int i=0;i<30;i++){
                 Thread.sleep(1000);
                 result2=redisService.getOrdinaryMatch(oppositeSex);//获得后就会直接删除
                 if(null!=result2){
                     break;
                 }
             }
              *//*Thread.sleep(blockTime);
               result2=redisService.getOrdinaryMatch(oppositeSex);//获得后就会直接删除*//*
              if(null==result2){
                  return MapHelper.error();
              }
        }else {

          }
        //  matchUserId= null==result1?Integer.parseInt(result2):Integer.parseInt(result1);//如果不为null那么就匹配到了
          matchUserId= Integer.parseInt(result2);//如果不为null那么就匹配到了
        //那么这两个人就出队列  匹配成功
        if(0 != matchUserId){
            //匹配到 就要把自己删除
            redisService.deleteOrdinaryMatch(userId,user.getSex()?1:0);
            //把两个用户信息给前端
            User matchUser = userService.get(matchUserId);
            map=MapHelper.success();
            map.put("matchUserId",matchUserId);
            map.put("matchUserHeadImg",matchUser.getHeadImg());
            map.put("matchUserName",matchUser.getName());
            return map;
        }
        return MapHelper.error();




    }*/



    /**
     * 思路： 生产者 消费者 模型 女为生产者入队  男为消费者 取
     * 女方消息 从redis中获取循环查找60秒    男 要把消息 加入到redis
     * @param token
     * @return
     * @throws SAException
     * @throws InterruptedException
     */
    @ApiOperation(value = "普通匹配", notes = "普通匹配")
    @RequestMapping(value ="ordinaryMatch" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> ordinaryMatch(@RequestHeader("token") @ApiParam(value = "权限校验") String token) throws SAException, InterruptedException {


        Map<String, Object> map = null;
        int userId = redisService.getUserId(token);
        int matchUserId = 0;//获得匹配成功的userId
        User user = userService.get(userId);
        int mysex = user.getSex() ? 1 : 0;//我的性别
        int matchUserId2 = 0;//女查找到男的 userId
        if (mysex == 0) {
            //如果是女的就入队
            redisService.addOrdinaryMatch(userId);
            for (int i = 0; i < 30; i++) {
                //搜索 自己有没有被匹配到
                matchUserId2 = redisService.getOrdinaryMatchImfore(userId);
                if (matchUserId2 != 0) {
                    break;
                } else {
                    Thread.sleep(2000);
                }
            }
            if (matchUserId2 != 0) {
                //女的匹配成功  返回男方信息
                map = MapHelper.success();
                User matchUser = userService.get(matchUserId2);
                map.put("matchUserId", matchUserId2);
                map.put("matchUserHeadImg", matchUser.getHeadImg());
                map.put("matchUserName", matchUser.getName());

                //新建通知
                Message message=new Message();
                message.setType(9);//
                message.setCyId(0);
                message.setSponsorId(matchUserId2);//发起者
                message.setUserId(userId);
                message.setTime(new Date());
                messageService.add(message);
                return map;
            } else {
                //没匹配到还有把自己从列表中删除
                redisService.deleteOrdinaryMatch(userId);
                return MapHelper.error("匹配失败");
            }


        } else {
            //男的就阻塞  查  阻塞60秒
            String match=redisService.blockGetOrdinaryMatch();
            if(null!=match){
                matchUserId = Integer.parseInt(match);
            }

            if(1==redisService.getDeleteOrdinaryMatchImform(userId)&&matchUserId != 0){
                //如果男方取消匹配后，但是  阻塞60的过程中接受到匹配  那就把拿到的女生重新入队
                redisService.addOrdinaryMatch(matchUserId);
                return MapHelper.error("匹配失败");
            }

            if (matchUserId != 0) {
                //匹配成功
                //通知被匹配的用户
                redisService.addOrdinaryMatchImfore(userId, matchUserId);

                User matchUser = userService.get(matchUserId);
                Message message=new Message();
                message.setType(9);//
                message.setCyId(0);
                message.setSponsorId(matchUserId2);//发起者
                message.setUserId(userId);
                message.setTime(new Date());
                messageService.add(message);

               map=MapHelper.success();
                map.put("matchUserId", matchUserId);
                map.put("matchUserHeadImg", matchUser.getHeadImg());
                map.put("matchUserName", matchUser.getName());
                return map;
            }

                return MapHelper.error("匹配失败");



        }
    }


    /**
     * 如果是女的 取消 立即删掉她在队中  男的加入redis通知  拿到女生后   判断有没有被取消，有就马上再把女生入队
     * @param token
     * @return
     * @throws SAException
     * @throws InterruptedException
     */
    @ApiOperation(value = "取消普通匹配", notes = "取消普通匹配")
    @RequestMapping(value ="cancelOrdinaryMatch" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> cancelOrdinaryMatch(@RequestHeader("token") @ApiParam(value = "权限校验") String token) throws SAException, InterruptedException {
        int userId = redisService.getUserId(token);
        int matchUserId = 0;//获得匹配成功的userId
        User user = userService.get(userId);
        int mysex = user.getSex() ? 1 : 0;//我的性别


        if(mysex==0){
            //如果是女生就出队
            redisService.deleteOrdinaryMatch(userId);
        }else {
            //男生  用redis通知
            redisService.deleteOrdinaryMatchImform(userId);
        }

        Map<String, Object> map =MapHelper.success();
        map.put("massge","取消匹配成功");
            return map;
    }



        /**
         * 思路 找到自己所有点赞记录  和 评论记录  之后  搜索 和自己 相同的   最后比较谁出现的次数多     另外，注意  还有屏蔽匹配的
         * @param token
         * @param
         * @return
         * @throws SAException
         */
    @ApiOperation(value = "开始灵魂匹配", notes = "开始灵魂匹配 一天匹配一次 ")
    @RequestMapping(value ="sendinvitation" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> sendinvitation(@RequestHeader("token") @ApiParam(value = "权限校验") String token) throws SAException {
        Map<String, Object> map =new HashMap<>();
        int userId=redisService.getUserId(token);
        //判断他今天匹配了没有
        /*if(1==redisService.getSendinvitationTime(userId)){
            map=MapHelper.error("每天只能匹配一次哦~~");
            return map;
        }*/

        List<Integer> users=new ArrayList<>();//找到所有的可以被匹配的用户
        List<Integer> usersLike=new ArrayList<>();//从点赞中找到的用户
        List<Integer> usersReview=new ArrayList<>();//从评论中找到的用户
        List<LikeInfo> likeInfos=likeInfoService.getByUserId(userId);
        for(LikeInfo likeInfo:likeInfos){
            List<Integer> temp=likeInfoService.getByCyidAndType(likeInfo.getCyId(),likeInfo.getType(),userId);
            if(null!=temp){
                usersLike.addAll(temp);
            }
        }
        //注意 如果一个用户评论 多个cyid  会有bug 这要用 set
        List<Review>  reviews=reviewService.getAllByuserId(userId);
        int nowReviewCyid=0;
       for(int i=0;i<reviews.size();i++){
           //如果该用户  对一表白多次评论 会造成 有多条这匹配 要筛选
            if(nowReviewCyid!=reviews.get(i).getCyId()){
                nowReviewCyid=reviews.get(i).getCyId();
                List<Integer> temp=reviewService.getAllbyCyid(reviews.get(i).getCyId(),userId);//要用set
                if(null!=temp){
                    usersReview.addAll(temp);
                }
            }

        }
         if(!usersLike.isEmpty()){
        users.addAll(usersLike);
         }
       if(!usersReview.isEmpty()){
           users.addAll(usersReview);
       }

        if(!users.isEmpty()){
            //找出现次数最多的用户

            //用户每天只能匹配一次  把用户存进redis  每天定时删除
           redisService.setSendinvitationTime(userId);



            users.sort((Integer a,Integer b)->a.compareTo(b));//

            int time=1;
            int currentTime=1;
            int currentUserId= users.get(0);
            for(int i=1;i<users.size();i++){

                if(users.get(i-1)==users.get(i)){
                    currentTime++;
                    if(currentTime>time){
                        time=currentTime;
                        currentUserId=users.get(i);
                    }
                }else {
                    currentTime=1;
                }
            }

            //redis
            redisService.addImformToRedis(currentUserId, RedisServiceImpl.Sendinvitation);//对方的userId
            Message message=new Message();
            message.setType(10);//
            message.setCyId(0);
            message.setSponsorId(userId);//发起者
            message.setUserId(currentUserId);//被接受到的用户
            message.setTime(new Date());
            messageService.add(message);
            User matchUser=userService.get(currentUserId);
            map=MapHelper.success();
            map.put("matchUserId",currentUserId);
            map.put("matchUserName",matchUser.getName());
            map.put("matchUserHeadImg",matchUser.getHeadImg());
            return map;
        }

        //如果没有匹配到
        map=MapHelper.error("没有合适的匹配对象，多去评论和点赞就有机会匹配到啦~~");
        return map;


    }


}
