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

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    private static Logger logger = Logger.getLogger(RedisServiceImpl.class);// 添加日志

    private  static  final int  saveState=1296000;//token保存的15天


    //必须用冒号    这样在redis中可以分组
    public static final String Wall="Wall:";//表白墙前缀

    private static final String Token="token:";//token前缀

    private static final String Imform="imform:";//所有通知的前缀   包括 收到的表白 喜欢   通知等

    public static final String Expression="expression:";//收到表白的前缀

    public static final String Like="like:";//收到点赞的前缀

    public static final String Comment="comment:";//收到评论的前缀

    public static final String LetterMessage="letterMessage";//收到时空邮局留言的前缀

    public static final String OrdinaryMatching="ordinaryMatching:";//收到匹配的前缀

    public static final String Sendinvitation="sendinvitation:";//收到灵魂匹配的前缀

    public static final String Imform2="imform2:";//收到通知的前缀

    public static final String dailySentence="dailySentence:";//每日一句前缀

    public static final String weekWord="weekWord:";//每日一句前缀

    public static final String spaceLimit="spaceLimit:";//时空邮局每天获取限制前缀

    public static final String DeleteOrdinaryMatching="deleteOrdinaryMatching:";//收到取消匹配的前缀

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public void addTokenToRedis(int userId, String token) {

        redisUtil.set(Token+token, String.valueOf(userId),saveState);

    }

    @Override
    public Boolean tokenExists(String token) {
        if(redisUtil.hasKey(Token+token)){
           return true;
        }else {
            return false;
        }

    }

    @Override
    public int getUserId(String token) throws SAException {
        if(!redisUtil.hasKey(Token+token)){
            throw new SAException("token过期","003");
        }

        String s=redisUtil.get(Token+token);

        int userId=Integer.parseInt(s);

        redisUtil.expire(token,saveState);

       return userId;


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
    public int deleteOneImformFromRedis(int userId, String type) {
        //获取之后  加一
        int time=0;
        if(redisUtil.hasKey(Imform+type+userId)){
            time=Integer.parseInt(redisUtil.get(Imform+type+userId));
            if(time>1){
                redisUtil.set(Imform+type+userId, ""+(--time));
            }else {
                redisUtil.del(Imform+type+userId);
            }
            return 1;
        }
        return 0;
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

    @Override
    public int addOrdinaryMatch(int userId) {
       return (int)redisUtil.rpush(OrdinaryMatching,""+userId);

    }

    @Override
    public String getOrdinaryMatch(int sex) {
        return null;
    }

    @Override
    public String getOrdinaryMatch( ) {
       return redisUtil.lpop(OrdinaryMatching);
    }

    @Override
    public long deleteOrdinaryMatch(int userId) {
        return redisUtil.listMove(OrdinaryMatching,-1,""+userId);
    }

    @Override
    public String blockGetOrdinaryMatch() {
        try {
            return redisUtil.blockLpop(OrdinaryMatching,60);
        }catch (Exception e){
            System.out.println("error");
            return null;
        }


    }

    @Override
    public int addOrdinaryMatchImfore(int userId, int matchUserId) {
        redisUtil.set(OrdinaryMatching+"imfore:"+matchUserId, ""+userId);
        return 0;
    }
    @Override
    public void setSpaceLetterLimit(int userId) {
        redisUtil.set(spaceLimit+userId,"1",86400);
    }

    @Override
    public int getOrdinaryMatchImfore(int userId) {
        if(!redisUtil.hasKey(OrdinaryMatching+"imfore:"+userId)){
            return 0;
        }

        String s=redisUtil.get(OrdinaryMatching+"imfore:"+userId);
        redisUtil.del(OrdinaryMatching+"imfore:"+userId);//获取到 消息后 立刻删除
        return  Integer.parseInt(s);
    }

    @Override
    public int deleteOrdinaryMatchImform(int userId) {
        redisUtil.set(DeleteOrdinaryMatching+userId, "1");
        return 1;
    }

    @Override
    public int getDeleteOrdinaryMatchImform(int userId) {
        if(redisUtil.hasKey(DeleteOrdinaryMatching+userId)){
            redisUtil.del(DeleteOrdinaryMatching+userId);
            return 1;
            }
        return 0;
    }

    @Override
    public boolean getSpaceLimit(int userId) {
        return redisUtil.hasKey(spaceLimit+userId);
    }

    @Override
    public void resetSpaceLimit() {
        Set<String> keys = redisUtil.keys(spaceLimit+"*");
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()){
            redisUtil.del(iterator.next());
        }
    }
}
