package com.yiban.suoai.service;


import com.yiban.suoai.pojo.DailySentence;

public interface DailySentenceService {
    DailySentence get(int id);

    int add(DailySentence cyinfor);

    void delete(int id);

    void update(DailySentence cyinfor);
}
