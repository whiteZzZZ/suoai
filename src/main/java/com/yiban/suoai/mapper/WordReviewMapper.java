package com.yiban.suoai.mapper;

import com.yiban.suoai.pojo.WordReview;
import com.yiban.suoai.pojo.WordReviewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WordReviewMapper {
    int countByExample(WordReviewExample example);

    int deleteByExample(WordReviewExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WordReview record);

    int insertSelective(WordReview record);

    List<WordReview> selectByExampleWithBLOBs(WordReviewExample example);

    List<WordReview> selectByExample(WordReviewExample example);

    WordReview selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WordReview record, @Param("example") WordReviewExample example);

    int updateByExampleWithBLOBs(@Param("record") WordReview record, @Param("example") WordReviewExample example);

    int updateByExample(@Param("record") WordReview record, @Param("example") WordReviewExample example);

    int updateByPrimaryKeySelective(WordReview record);

    int updateByPrimaryKeyWithBLOBs(WordReview record);

    int updateByPrimaryKey(WordReview record);
}