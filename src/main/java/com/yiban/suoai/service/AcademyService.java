package com.yiban.suoai.service;

import com.yiban.suoai.pojo.Academy;

public interface AcademyService {
    void add(Academy academy);

    void update(Academy academy);

    Academy get(int id);
}
