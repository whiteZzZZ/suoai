package com.yiban.suoai.service;

import com.yiban.suoai.pojo.Notice;

import java.util.List;

public interface NoticeService {

    int add(Notice notice);

    int delete(int id);

    Notice get(int id);

    List<Notice>  getAllByUserId(int userId);
}
