package com.yiban.suoai.service.impl;


import com.yiban.suoai.mapper.ReviewMapper;
import com.yiban.suoai.pojo.Review;
import com.yiban.suoai.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl  implements ReviewService {

    @Autowired
    ReviewMapper reviewMapper;

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
}
