package com.yiban.suoai.service;

import com.yiban.suoai.forepojo.ForeLetterMessage;
import com.yiban.suoai.pojo.LetterMessage;

import java.util.List;

public interface LetterMessageService {
    void insert(LetterMessage letterMessage);

    List<LetterMessage> get(int letterId);

    List<ForeLetterMessage> full(List<LetterMessage> letterMessages);

    LetterMessage getById(int letterId);

    /**
     * 获取该空间邮局 的第一条留言
     * @param letterId
     * @return
     */
    LetterMessage getTheFirth(int letterId);

}
