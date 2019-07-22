package com.yiban.suoai.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.service.WallService;
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

    //每隔2秒执行一次
    @Scheduled(fixedRate = 2000)
    public void testTasks1() {
       // System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
    }

    //每天0点执行
    @Scheduled(cron = "0 00 00 * * ?")
    public void testTasks() throws SAException {
        //System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
        wallService.updateWall();
    }


}