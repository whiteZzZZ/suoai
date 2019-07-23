package com.yiban.suoai.service.impl;

import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.service.RedisService;

import com.yiban.suoai.util.RedisUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    private static Logger logger = Logger.getLogger(RedisServiceImpl.class);// 添加日志

    private  static  final int  saveState=1296000;//token保存的15天

   // private final JedisPool Pool = RedisAPI.getPool();//

    //必须用冒号    这样在redis中可以分组
    public static final String Wall="Wall:";//表白墙前缀

    private static final String Token="token:";//token前缀

    private static final String Imform="imform:";//所有通知的前缀   包括 收到的表白 喜欢   通知等

    public static final String Expression="expression:";//收到表白的前缀

    public static final String Like="like:";//收到点赞的前缀

    public static final String Comment="comment:";//收到评论的前缀

    public static final String Matching="matching:";//收到匹配的前缀

    public static final String Imform2="imform2:";//收到通知的前缀









    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public void addTokenToRedis(int userId, String token) {

        redisUtil.set(Token+token, String.valueOf(userId),saveState);

       /* Jedis redis =Pool.getResource();

        redis.set(token,String.valueOf(userId));

        redis.expire(token,saveState);//有效期为15天

        redis.close();*/
    }

    @Override
    public int getUserId(String token) throws SAException {

        if(!redisUtil.hasKey(token)){
            throw new SAException("token过期","003");
        }

        String s=redisUtil.get(Token+token);

        int userId=Integer.parseInt(s);

        redisUtil.expire(token,saveState);

       return userId;

        /*Jedis redis =Pool.getResource();

        int userId;

        if(redis.exists(token)){
            redis.expire(token,saveState);
            userId=Integer.parseInt(redis.get(token));
            redis.close();
            return  userId;
        }else {
            redis.close();
            throw new SAException("token过期","003");
        }
*/
    }

    @Override
    public void addImformToRedis(int userId,String type) {
        //获取之后  加一
        int time=0;
        if(redisUtil.hasKey(Imform+type+userId)){
             time=Integer.parseInt(redisUtil.get(Imform+type+userId));
            redisUtil.set(Imform+type+userId, ""+(++time));
        }else {
            redisUtil.set(Imform+type+userId,"1");
        }

    }

    @Override
    public int getImformFromRedis(int userId,String type) {
       if(redisUtil.hasKey(Imform+type+userId)){
            int time=Integer.parseInt(redisUtil.get(Imform+type+userId));
            return time;
        }else{
           return 0;
       }
    }

    @Override
    public int deleteImformFromRedis(int userId,String type) {
        if(redisUtil.hasKey(Imform+type+userId)){
            redisUtil.del(Imform+type+userId);
            return 1;
        }else{
            return 0;
        }
    }



}
