package com.yiban.suoai.service;


import com.yiban.suoai.pojo.School;

import java.util.List;

public interface SchoolService {
    void add(School school);

    void update(School school);

    School get(int id);

    List<School> selectByName(String schoolName);
}
