package com.yiban.suoai.service;

import com.yiban.suoai.pojo.Image;

public interface ImageService {

    void add(Image image);

    Image get(int id);

    void delete(int id);
}
