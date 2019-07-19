package com.yiban.suoai.service.impl;

import com.yiban.suoai.mapper.ImageMapper;
import com.yiban.suoai.pojo.Image;
import com.yiban.suoai.pojo.ImageExample;
import com.yiban.suoai.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Image> getByCyid(int cyid) {
        ImageExample example=new ImageExample();
        example.createCriteria().andCy_idEqualTo(cyid);
        List<Image> list=imageMapper.selectByExample(example);
        if(list.isEmpty()){
            return null;
        }
        return list;
    }
}
