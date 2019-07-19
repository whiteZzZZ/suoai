package com.yiban.suoai.service;

import com.yiban.suoai.forepojo.ForeCyinfor;
import com.yiban.suoai.pojo.Cyinfor;

import java.util.List;

public interface CyinforService {

    Cyinfor get(int id);

    int add(Cyinfor cyinfor);

    void delete(int id);

    /**
     * 填充
     * @param userid
     * @param privacy
     * @param hide
     * @param who
     * @param hasImage
     * @param text
     * @return
     */
    Cyinfor full(int userid,Boolean privacy,Boolean hide,int who,int hasImage,String text);

    List<Cyinfor> getAll();

    List<ForeCyinfor> foreFull(List<Cyinfor> cyinfors);

}
