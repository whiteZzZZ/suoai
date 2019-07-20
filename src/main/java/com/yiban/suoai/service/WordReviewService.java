package com.yiban.suoai.service;

import com.yiban.suoai.pojo.WordReview;

public interface WordReviewService {

    WordReview get(int id);

    void delete(int id);

    void add(WordReview wordReview);

    void update(WordReview wordReview);
}
