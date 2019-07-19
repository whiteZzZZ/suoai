package com.yiban.suoai.service;

import com.yiban.suoai.pojo.Image;

import java.util.List;

public interface ImageService {

    void add(Image image);

    Image get(int id);

    void delete(int id);

    List<Image> getByCyid(int cyid);

}
