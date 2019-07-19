package com.yiban.suoai.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileHelper {

    public final static String forePath="D:/image";//todo  记得修改  路径
    public final static String cyinfor="/cyinfor";//cyinfor路径
    public final static String dailySentence="/dailySentence";//每日一句的路径
    public final static String weekWord="/weekWord";//每日一句的路径
    public final static String headImg="/headImg";//头像路径

    // final String imagePath="";

    public static String FileSave( MultipartFile file,String uuid,String subpath) throws IOException {
        InputStream in = file.getInputStream();
        OutputStream out = new FileOutputStream(new File(forePath+"/"+uuid+".jpg"));
        byte[] b = new byte[1024];
        int len;
        while((len =in.read(b))!= -1) {
            out.write(b, 0, len);
        }
        in.close();
        out.close();
        return forePath+"/"+uuid+".jpg";
    }
}
