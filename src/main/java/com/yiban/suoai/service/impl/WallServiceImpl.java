package com.yiban.suoai.service.impl;

import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.pojo.Cyinfor;
import com.yiban.suoai.pojo.Wall;
import com.yiban.suoai.service.CyinforService;
import com.yiban.suoai.service.WallService;
import com.yiban.suoai.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WallServiceImpl  implements WallService {

    @Autowired
    CyinforService cyinforService;
    @Autowired
    RedisUtil redisUtil;



    @Override
    public void updateWall() throws SAException {
        List<Cyinfor> cyinfors=cyinforService.topTen();
        redisUtil.setObject(RedisServiceImpl.Wall,cyinfors);
    }

    @Override
    public  List<Cyinfor> get() {
        return   redisUtil.getObjectList(RedisServiceImpl.Wall);
    }


}
