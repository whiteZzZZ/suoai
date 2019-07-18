package com.yiban.suoai.service.impl;

import com.yiban.suoai.mapper.ImageMapper;
import com.yiban.suoai.pojo.Image;
import com.yiban.suoai.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageMapper imageMapper;


    @Override
    public void add(Image image) {
        imageMapper.insert(image);
    }

    @Override
    public Image get(int id) {
        return  imageMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(int id) {
        imageMapper.deleteByPrimaryKey(id);
    }
}
