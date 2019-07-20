package com.yiban.suoai.service.impl;


import com.yiban.suoai.forepojo.ForeCyinfor;
import com.yiban.suoai.forepojo.ForeReview;
import com.yiban.suoai.mapper.ReviewMapper;
import com.yiban.suoai.pojo.LikeInfo;
import com.yiban.suoai.pojo.Review;
import com.yiban.suoai.pojo.ReviewExample;
import com.yiban.suoai.pojo.User;
import com.yiban.suoai.service.LikeInfoService;
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
    @Autowired
    LikeInfoService likeInfoService;

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
        review.setIsDelete(true);
        update(review);
    }

    @Override
    public void update(Review review) {
        reviewMapper.updateByPrimaryKeySelective(review);
    }

    @Override
    public Review full(int cyid, int userId, String text, int replyId) {
        Review review=new Review();
        review.setCyId(cyid);
        review.setUserId(userId);
        review.setLikeTime(0);
        review.setReplyId(replyId);
        review.setTime(new Date());
        review.setContent(text);
        review.setReviewTime(0);
        review.setIsDelete(false);
        return review;
    }

    @Override
    public List<Review> getAllButReply(int cyid) {
        ReviewExample example=new ReviewExample();
        example.createCriteria().andCyIdEqualTo(cyid).andReplyIdEqualTo(0);//不获取评论的评论
        List<Review> list=reviewMapper.selectByExampleWithBLOBs(example);
        if(list.isEmpty()){
            return null;
        }
        return list;
    }

    @Override
    public List<ForeReview> foreFull(List<Review> list,int userId) {
        List<ForeReview> foreCyinfors=new ArrayList<>();
        for(Review review:list){
            ForeReview foreReview = new ForeReview();
            foreReview.setReviewId(review.getId());
            foreReview.setCy_id(review.getCyId());
            foreReview.setUser_id(review.getUserId());
            foreReview.setLike_time(review.getLikeTime());
            foreReview.setReview_time(review.getReviewTime());
            foreReview.setContent(review.getContent());
            foreReview.setTime(review.getTime());
            User user = userService.get(review.getUserId());
            foreReview.setHead_img(user.getHeadImg());
            foreReview.setName(user.getName());

            LikeInfo likeInfo=likeInfoService.getByCyidAndUserIdAndType(review.getId(),userId,1);
            if(null!=likeInfo){
                foreReview.setIfLike(true);
            }

            if(0!=review.getReplyId()){
                //如果该评论是评论的评论  则还要显示回复的内容和回复人的name
                foreReview.setReply_id(review.getReplyId());
                Review replyReview = this.get(review.getReplyId());
                int replyUserId=replyReview.getUserId();//获取回复谁的用户id
                User replyUser=userService.get(replyUserId);
                foreReview.setReply_user_id(replyUserId);
                foreReview.setReply_content(replyReview.getContent());
                foreReview.setReply_name(replyUser.getName());
            }
            foreCyinfors.add(foreReview);
        }
        return foreCyinfors;
    }

    @Override
    public List<Review> getAllByReplyId(int reviewId) {
        List<Review> reviews=new ArrayList<>();
        ReviewExample example=new ReviewExample();
        example.createCriteria().andReplyIdEqualTo(reviewId);
        example.setOrderByClause("id desc");
        reviews=reviewMapper.selectByExample(example);
        if(reviews.isEmpty()){
            return null;
        }
        return reviews;
    }

    @Override
    public List<ForeReview> foreFullSecondaryComments(List<Review> list,int userId) {
        List<ForeReview> foreCyinfors=new ArrayList<>();
        for(Review review:list){
            ForeReview foreReview = new ForeReview();
            foreReview.setReviewId(review.getId());
            foreReview.setCy_id(review.getCyId());
            foreReview.setUser_id(review.getUserId());
            foreReview.setLike_time(review.getLikeTime());
            foreReview.setReview_time(review.getReviewTime());
            foreReview.setContent(review.getContent());
            foreReview.setTime(review.getTime());
            User user = userService.get(review.getUserId());
            foreReview.setHead_img(user.getHeadImg());
            foreReview.setName(user.getName());
            foreReview.setReply_id(review.getReplyId());
            LikeInfo likeInfo=likeInfoService.getByCyidAndUserIdAndType(review.getId(),userId,1);
            if(null!=likeInfo){
                foreReview.setIfLike(true);
            }
            foreCyinfors.add(foreReview);
        }
        return foreCyinfors;
    }
}
