package com.yiban.suoai.service.impl;

import com.yiban.suoai.pojo.Wall;
import com.yiban.suoai.service.CyinforService;
import com.yiban.suoai.service.WallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WallServiceImpl  implements WallService {

    @Autowired
    CyinforService cyinforService;

    @Override
    public void updateWall() {

    }
}
