package com.yiban.suoai.service;

import com.yiban.suoai.pojo.Title;

import java.util.List;


public interface TitleService {
    void add(Title user);

    void update(Title user);

    Title get(int id);

    List<Title> getByUserId(int userId);
}
