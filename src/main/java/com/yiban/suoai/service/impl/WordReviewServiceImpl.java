package com.yiban.suoai.service.impl;

import com.yiban.suoai.mapper.WordReviewMapper;
import com.yiban.suoai.pojo.WordReview;
import com.yiban.suoai.service.WordReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordReviewServiceImpl implements WordReviewService {

    @Autowired
    WordReviewMapper wordReviewMapper;

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
}
