package com.yiban.suoai.service;

import com.yiban.suoai.pojo.Tip;
import com.yiban.suoai.pojo.TipBank;

import java.util.List;

public interface TipService {
    int addTip(Tip tip);
    int deleteTip(int id);
    int checkTip(int userId,List<TipBank> list);
    List<Tip> getExam(int userId);
}
