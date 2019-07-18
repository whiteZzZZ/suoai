package com.yiban.suoai.service;

import com.yiban.suoai.pojo.Review;

public interface ReviewService {

    void add(Review review);

    Review get(int id);

    void delete(int id);

    void update(Review review);
}
