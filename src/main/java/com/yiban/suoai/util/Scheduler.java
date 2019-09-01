package com.yiban.suoai.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.service.DailySentenceService;
import com.yiban.suoai.service.RedisService;
import com.yiban.suoai.service.WallService;
import com.yiban.suoai.service.WeekWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时器
 *
 */
@Component
public class Scheduler{
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    WallService wallService;
    @Autowired
    DailySentenceService dailySentenceService;
    @Autowired
    WeekWordService weekWordService;
    @Autowired
    RedisService redisService;
    //每隔2秒执行一次
    @Scheduled(fixedRate = 2000)
    public void testTasks1() {
       // System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
    }

    //每隔一个星期执行一次
    @Scheduled(fixedRate = 604800000)
    public void testTasks2() {
        // System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
//        weekWordService.updateByWeek();每周一话更新
    }

    //每天0点执行
    @Scheduled(cron = "0 00 00 * * ?")
    public void testTasks() throws SAException {
        //System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
        //wallService.updateWall();
        dailySentenceService.updateByDay();
        redisService.resetSpaceLimit();
        redisService.deleteSendinvitationTime();//灵魂匹配次数 删除
        weekWordService.updateByWeek();
    }


}