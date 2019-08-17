package com.yiban.suoai.service.impl;

import com.yiban.suoai.forepojo.ForeReview;
import com.yiban.suoai.forepojo.ForeWordReview;
import com.yiban.suoai.mapper.WordReviewMapper;
import com.yiban.suoai.pojo.*;
import com.yiban.suoai.service.LikeInfoService;
import com.yiban.suoai.service.UserService;
import com.yiban.suoai.service.WordReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WordReviewServiceImpl implements WordReviewService {

    @Autowired
    WordReviewMapper wordReviewMapper;
    @Autowired
    UserService userService;
    @Autowired
    LikeInfoService likeInfoService;

    @Override
    public WordReview get(int id) {
        return wordReviewMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(int id) {
        wordReviewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void add(WordReview wordReview) {
        wordReviewMapper.insert(wordReview);
    }

    @Override
    public void update(WordReview wordReview) {
        wordReviewMapper.updateByPrimaryKey(wordReview);
    }

    @Override
    public WordReview full(int id, String text,int userId,int paperId) {
        WordReview wordReview = new WordReview();
        wordReview.setContent(text);
        wordReview.setIsDelete(false);
        wordReview.setLikeTime(0);
        wordReview.setPaper(paperId);
        wordReview.setReviewTime(0);
        wordReview.setTime(new Date());
        wordReview.setUserId(userId);
        wordReview.setWordId(id);
        return wordReview;
    }

    @Override
    public List<WordReview> getAll(int weekWordId) {
        WordReviewExample wordReviewExample = new WordReviewExample();
        wordReviewExample.createCriteria().andWordIdEqualTo(weekWordId).andIsDeleteEqualTo(false);
        wordReviewExample.setOrderByClause("id desc");
        return wordReviewMapper.selectByExampleWithBLOBs(wordReviewExample);
    }

    @Override
    public List<ForeWordReview> foreFull(List<WordReview> list, int userId) {
        List<ForeWordReview> foreCyinfors=new ArrayList<>();
        for(WordReview review:list) {
            ForeWordReview foreReview = new ForeWordReview();
            foreReview.setReviewId(review.getId());
            foreReview.setWord_id(review.getWordId());
            foreReview.setUser_id(review.getUserId());
            foreReview.setLike_time(review.getLikeTime());
            foreReview.setReview_time(review.getReviewTime());
            foreReview.setContent(review.getContent());
            foreReview.setTime(review.getTime());
            User user = userService.get(review.getUserId());
            foreReview.setHead_img(user.getHeadImg());
            foreReview.setName(user.getName());

            LikeInfo likeInfo = likeInfoService.getByCyidAndUserIdAndType(review.getId(), userId, 8);
            if (null != likeInfo) {
                foreReview.setIfLike(true);//该用户是否对 这个评论点赞
            }
            foreCyinfors.add(foreReview);
        }
        return foreCyinfors;
    }
}
