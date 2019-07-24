package com.yiban.suoai.service;

import com.yiban.suoai.pojo.Inform;

public interface InformService {
    void add(Inform inform);

    void update(Inform inform);

    Inform get(Integer id);
}
