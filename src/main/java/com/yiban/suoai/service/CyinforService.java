package com.yiban.suoai.service;

import com.yiban.suoai.exception.SAException;
import com.yiban.suoai.forepojo.ForeCyinfor;
import com.yiban.suoai.pojo.Cyinfor;

import java.util.List;

public interface CyinforService {

    Cyinfor get(int id);

    int add(Cyinfor cyinfor);

    void delete(int id);

    void update(Cyinfor cyinfor);

    /**
     * 填充
     * @param userid
     * @param privacy
     * @param hide
     * @param who
     * @param hasImage
     * @param text
     * @return
     */
    Cyinfor full(int userid,Boolean privacy,Boolean hide,int who,int hasImage,String text);

    List<Cyinfor> getAll();

    /**
     * 获取自己发布的表白
     * @param userId
     * @return
     */
    List<Cyinfor> getByUserId(int userId);

    List<ForeCyinfor> foreFull(List<Cyinfor> cyinfors,int userId);

    /**
     * 返回表白墙点赞前十名  点赞数相同按照 评论数排
     * @return
     */
    List<Cyinfor> topTen() throws SAException;

}
