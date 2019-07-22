package com.yiban.suoai.service;

import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.pojo.Cyinfor;

import java.util.List;

public interface WallService {

    void updateWall() throws SAException;

    List<Cyinfor> get();
}
