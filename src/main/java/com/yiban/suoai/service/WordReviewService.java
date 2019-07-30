package com.yiban.suoai.service;

import com.yiban.suoai.forepojo.ForeReview;
import com.yiban.suoai.forepojo.ForeWordReview;
import com.yiban.suoai.pojo.Review;
import com.yiban.suoai.pojo.WordReview;

import java.util.List;

public interface WordReviewService {

    WordReview get(int id);

    void delete(int id);

    void add(WordReview wordReview);

    void update(WordReview wordReview);

    WordReview full(int id,String text,int userId,int paperId);

    List<WordReview> getAll(int weekWordId);

    /**
     * 填充给前端的数据
     * @param list
     * @return
     */
    List<ForeWordReview> foreFull(List<WordReview> list, int userId);
}
