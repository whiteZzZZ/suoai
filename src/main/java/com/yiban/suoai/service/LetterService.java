package com.yiban.suoai.service;

import com.yiban.suoai.forepojo.ForeLetter;
import com.yiban.suoai.forepojo.ForeSpaceLetter;
import com.yiban.suoai.pojo.Letter;

import java.util.List;

public interface LetterService {
    List<Letter> getOrderByTime(int userId);

    List<ForeLetter> full(List<Letter> letters);

    Letter get(int letterId);

    void update(Letter letter);

    void insert(Letter letter);

    List<Letter> getSpaceLetter();

    List<ForeSpaceLetter> full2(List<Letter> letters);
}
