package com.yiban.suoai.util;


import com.yiban.suoai.exception.SAException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author 言曌
 * @date 2018/12/16 下午6:57
 */
@Component
public class RedisUtil {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    // Key（键），简单的key-value操作

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    /**
     * 实现命令：TTL key，以秒为单位，返回给定 key的剩余生存时间(TTL, time to live)。
     *
     * @param key
     * @return
     */
    public long ttl(String key) {
        return stringRedisTemplate.getExpire(key);
    }
    /**
     * 实现命令：expire 设置过期时间，单位秒
     *
     * @param key
     * @return
     */
    public void expire(String key, long timeout) {
        stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }
    /**
     * 实现命令：INCR key，增加key一次
     *
     * @param key
     * @return
     */
    public long incr(String key, long delta) {
        return stringRedisTemplate.opsForValue().increment(key, delta);
    }
    /**
     * 实现命令： key，减少key一次
     *
     * @param key
     * @return
     */
    public long decr(String key, long delta) {
        if(delta<0){
//            throw new RuntimeException("递减因子必须大于0");
            del(key);
            return 0;
        }
        return stringRedisTemplate.opsForValue().increment(key, -delta);
    }
    /**
     * 实现命令：KEYS pattern，查找所有符合给定模式 pattern的 key
     */
    public Set<String> keys(String pattern) {
        return stringRedisTemplate.keys(pattern);
    }
    /**
     * 实现命令：DEL key，删除一个key
     *
     * @param key
     */
    public void del(String key) {
        stringRedisTemplate.delete(key);
    }
    // String（字符串）
    /**
     * 实现命令：SET key value，设置一个key-value（将字符串值 value关联到 key）
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }
    /**
     * 实现命令：SET key value EX seconds，设置key-value和超时时间（秒）
     *
     * @param key
     * @param value
     * @param timeout （以秒为单位）
     */
    public void set(String key, String value, long timeout) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }
    /**
     * 实现命令：GET key，返回 key所关联的字符串值。
     *
     * @param key
     * @return value
     */
    public String get(String key) {
        return (String) stringRedisTemplate.opsForValue().get(key);
    }
    // Hash（哈希表）
    /**
     * 实现命令：HSET key field value，将哈希表 key中的域 field的值设为 value
     *
     * @param key
     * @param field
     * @param value
     */
    public void hset(String key, String field, Object value) {
        stringRedisTemplate.opsForHash().put(key, field, value);
    }
    /**
     * 实现命令：HGET key field，返回哈希表 key中给定域 field的值
     *
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field) {
        return (String) stringRedisTemplate.opsForHash().get(key, field);
    }
    /**
     * 实现命令：HDEL key field [field ...]，删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     *
     * @param key
     * @param fields
     */
    public void hdel(String key, Object... fields) {
        stringRedisTemplate.opsForHash().delete(key, fields);
    }
    /**
     * 实现命令：HGETALL key，返回哈希表 key中，所有的域和值。
     *
     * @param key
     * @return
     */
    public Map<Object, Object> hgetall(String key) {
        return stringRedisTemplate.opsForHash().entries(key);
    }
    // List（列表）
    /**
     * 实现命令：LPUSH key value，将一个值 value插入到列表 key的表头
     *
     * @param key
     * @param value
     * @return 执行 LPUSH命令后，列表的长度。
     */
    public long lpush(String key, String value) {
        return stringRedisTemplate.opsForList().leftPush(key, value);
    }
    /**
     * 实现命令：LPOP key，移除并返回列表 key的头元素。
     *
     * @param key
     * @return 列表key的头元素。
     */
    public String lpop(String key) {
        return (String) stringRedisTemplate.opsForList().leftPop(key);
    }


    /**
     * 阻塞队列   如果拿不到 线程会阻塞60秒
     * @param key
     * @return
     */
    public String blockLpop(String key,int seconds) {
        return (String) stringRedisTemplate.opsForList().leftPop(key,seconds,TimeUnit.SECONDS);
    }





    /**
     * 实现命令：RPUSH key value，将一个值 value插入到列表 key的表尾(最右边)。
     *
     * @param key
     * @param value
     * @return 执行 LPUSH命令后，列表的长度。
     */
    public long rpush(String key, String value) {
        return stringRedisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * List中删除指定的元素  count> 0：删除等于从左到右移动的值的第一个元素；count< 0：删除等于从右到左移动的值的第一个元素；count = 0：删除等于value的所有元素。
     * @param key
     * @param count
     * @param value
     * @return
     */
    public long listMove(String  key, long count, Object value){
        return stringRedisTemplate.opsForList().remove(key,count,value);
    }

    public Object getObject(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public void setObject(String key,Object object){
        redisTemplate.opsForValue().set(key,object);
    }

    public void setObject(String key,Object object,long timeout){
        redisTemplate.opsForValue().set(key,object,timeout,TimeUnit.SECONDS);
    }

    public Set<String> objectKeys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    public void setObjectList(String key, List list){
        redisTemplate.opsForValue().set(key,list);
    }

    public List getObjectList(String key){
        return (List)redisTemplate.opsForValue().get(key);
    }

    public boolean hasKey(String key){
        return stringRedisTemplate.hasKey(key);
    }

    public boolean hasObjectKey(String key){
        return redisTemplate.hasKey(key);
    }

    public long lpushObject(String key,Object value){return redisTemplate.opsForList().leftPush(key,value);}

    public long rpushObject(String key,Object value){return redisTemplate.opsForList().rightPush(key,value);}

    public Object lpop(String key,Object value){return redisTemplate.opsForList().leftPop(key);}

    public List getList(String key){
        long n = redisTemplate.opsForList().size(key);
        return redisTemplate.opsForList().range(key,0,n);
    }

    public boolean hasToken(String token){
        return stringRedisTemplate.hasKey("token:"+token);
    }

    public int getUserId(String token)throws SAException {
        System.out.println("in get ID");
        if(stringRedisTemplate.hasKey("token:"+token)){
            System.out.println("in id token"+token);
            System.out.println(Integer.parseInt(stringRedisTemplate.opsForValue().get("token:"+token)));
            return Integer.parseInt(stringRedisTemplate.opsForValue().get("token:"+token));
        }
            throw new SAException(ErrorCode.TOKEN_FAILURE);
    }

    public List getObjList(String key){
        return redisTemplate.opsForList().range(key,0,-1);
    }

    public boolean delObject(String key){
        return redisTemplate.delete(key);
    }
}