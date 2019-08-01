package com.yiban.suoai.service;

import com.yiban.suoai.forepojo.ForeCyinfor;
import com.yiban.suoai.forepojo.ForeReview;
import com.yiban.suoai.pojo.Review;

import java.util.List;

public interface ReviewService {

    void add(Review review);

    Review get(int id);

    void delete(int id);

    void update(Review review);

    Review full(int cyid,int userId,String text,int replyId);

    /**
     * 获取表白的所有评论，但不获取评论的评论
     * @param cyid
     * @return
     */
    List<Review> getAllButReply(int cyid);

    /**
     * 填充给前端的数据
     * @param list
     * @return
     */
    List<ForeReview> foreFull(List<Review> list,int userId);

    List<Review> getAllByReplyId(int reviewId);

    /**
     * 二级评论 不需要显示对方的用户的名称
     * @param list
     * @return
     */
    List<ForeReview> foreFullSecondaryComments(List<Review> list,int userId);

    /**
     * 获取自己所有的评论
     * @param userId
     * @return
     */
    List<Review> getAllByuserId(int userId);

    List<Integer> getAllbyCyid(int cyId,int userId);

}
