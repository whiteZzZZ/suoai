package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.WordReview;
import com.yiban.suoai.pojo.WordReviewExample;
import java.util.List;

public interface WordReviewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WordReview record);

    int insertSelective(WordReview record);

    List<WordReview> selectByExampleWithBLOBs(WordReviewExample example);

    List<WordReview> selectByExample(WordReviewExample example);

    WordReview selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WordReview record);

    int updateByPrimaryKeyWithBLOBs(WordReview record);

    int updateByPrimaryKey(WordReview record);
}