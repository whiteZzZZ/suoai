package com.yiban.suoai.service.impl;


import com.yiban.suoai.forepojo.ForeCyinfor;
import com.yiban.suoai.forepojo.ForeReview;
import com.yiban.suoai.mapper.ReviewMapper;
import com.yiban.suoai.pojo.Review;
import com.yiban.suoai.pojo.ReviewExample;
import com.yiban.suoai.pojo.User;
import com.yiban.suoai.service.ReviewService;
import com.yiban.suoai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReviewServiceImpl  implements ReviewService {

    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    UserService userService;

    @Override
    public void add(Review review) {
        reviewMapper.insert(review);
    }

    @Override
    public Review get(int id) {
        return reviewMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(int id) {
        Review review=get(id);
        review.setIs_delete(true);
        update(review);
    }

    @Override
    public void update(Review review) {
        reviewMapper.updateByPrimaryKeySelective(review);
    }

    @Override
    public Review full(int cyid, int userId, String text, int replyId) {
        Review review=new Review();
        review.setCy_id(cyid);
        review.setUser_id(userId);
        review.setLike_time(0);
        review.setReply_id(0);
        review.setTime(new Date());
        review.setContent(text);
        review.setIs_delete(false);
        return review;
    }

    @Override
    public List<Review> getAllButReply(int cyid) {
        ReviewExample example=new ReviewExample();
        example.createCriteria().andCy_idEqualTo(cyid).andReply_idEqualTo(0);//不获取评论的评论
        List<Review> list=reviewMapper.selectByExampleWithBLOBs(example);
        if(list.isEmpty()){
            return null;
        }
        return list;
    }

    @Override
    public List<ForeReview> foreFull(List<Review> list) {
        List<ForeReview> foreCyinfors=new ArrayList<>();
        for(Review review:list){
            ForeReview foreReview = new ForeReview();
            foreReview.setReviewId(review.getId());
            foreReview.setCy_id(review.getCy_id());
            foreReview.setUser_id(review.getUser_id());
            foreReview.setLike_time(review.getLike_time());
            foreReview.setReview_time(review.getReview_time());
            foreReview.setContent(review.getContent());
            foreReview.setTime(review.getTime());
            User user = userService.get(review.getUser_id());
            foreReview.setHead_img(user.getHead_img());
            foreReview.setName(user.getName());


            if(0!=review.getReply_id()){
                //如果该评论是评论的评论  则还要显示回复的内容和回复人的name
                foreReview.setReply_id(review.getReply_id());
                Review replyReview = this.get(review.getReply_id());
                int replyUserId=replyReview.getUser_id();//获取回复谁的用户id
                User replyUser=userService.get(replyUserId);
                foreReview.setReply_user_id(replyUserId);
                foreReview.setReply_content(replyReview.getContent());
                foreReview.setReply_name(replyUser.getName());
            }
            foreCyinfors.add(foreReview);
        }
        return foreCyinfors;
    }
}
