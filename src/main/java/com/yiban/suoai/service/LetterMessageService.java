package com.yiban.suoai.service;

import com.yiban.suoai.forepojo.ForeLetterMessage;
import com.yiban.suoai.pojo.LetterMessage;

import java.util.List;

public interface LetterMessageService {
    void insert(LetterMessage letterMessage);

    List<LetterMessage> get(int letterId);

    List<ForeLetterMessage> full(List<LetterMessage> letterMessages,int userId);
}
