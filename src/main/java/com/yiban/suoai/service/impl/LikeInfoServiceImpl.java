package com.yiban.suoai.service.impl;

import com.yiban.suoai.mapper.LikeInfoMapper;
import com.yiban.suoai.pojo.LikeInfo;
import com.yiban.suoai.pojo.LikeInfoExample;
import com.yiban.suoai.service.LikeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikeInfoServiceImpl implements LikeInfoService {

    @Autowired
    LikeInfoMapper likeInfoMapper;

    @Override
    public void add(LikeInfo likeInfo) {
        likeInfoMapper.insert(likeInfo);
    }

    @Override
    public LikeInfo get(int id) {
        return likeInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(int id) {
        likeInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public LikeInfo getByCyidAndUserIdAndType(int cyid, int userId, int type) {
        LikeInfoExample example=new LikeInfoExample();
        example.createCriteria().andCyIdEqualTo(cyid).andUserIdEqualTo(userId).andTypeEqualTo((byte)type);
        List<LikeInfo> likeInfos= likeInfoMapper.selectByExample(example);
        if(likeInfos.isEmpty()){
            return null;
        }
        return likeInfos.get(0);
    }

    @Override
    public List<Integer> getByCyidAndType(int cyid, int type,int userId) {
       LikeInfoExample example=new LikeInfoExample();
       example.createCriteria().andCyIdEqualTo(cyid).andTypeEqualTo((byte) type);
       List<LikeInfo> likeInfos=likeInfoMapper.selectByExample(example);
       if(likeInfos.isEmpty()){
           return null;
       }
       List<Integer>  list =new ArrayList<>();
       for(LikeInfo likeInfo:likeInfos){
           if(likeInfo.getUserId()!=userId){
               list.add(likeInfo.getUserId());
           }

       }
       return list;

    }

    @Override
    public List<LikeInfo> getByUserId(int userId) {
        LikeInfoExample example=new LikeInfoExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<LikeInfo> likeInfos=likeInfoMapper.selectByExample(example);
        if(likeInfos.isEmpty()){
            return null;
        }
        return likeInfos;
    }
}
