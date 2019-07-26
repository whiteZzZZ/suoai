package com.yiban.suoai.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class FileHelper {

    public final static String forePath="/image";//todo  记得修改  路径
   // public final static String forePath="D:/image";//todo  记得修改  路径
    public final static String cyinfor="/cyinfor";//cyinfor路径
    public final static String dailySentence="/dailySentence";//每日一句的路径
    public final static String weekWord="/weekWord";//每日一句的路径
    public final static String headImg="/headImg";//头像路径

    // final String imagePath="";

    public static String FileSave( MultipartFile file,String uuid,String subpath) throws IOException {
        InputStream in = file.getInputStream();
        File file1=new File(forePath+subpath);
        if(!file1.exists()){
            file1.mkdirs();
        }
        OutputStream out = new FileOutputStream(new File(forePath+subpath+"/"+uuid+"-y.jpg"));//原图在缩略图的名称基础上加-y
        byte[] b = new byte[1024];
        int len;
        while((len =in.read(b))!= -1) {
            out.write(b, 0, len);
        }
        in.close();
        out.close();
        return forePath+subpath+"/"+uuid+".jpg";
    }

    /**
     * 压缩图片  压缩图片的路径为原来图片的路径加   -y
     * @param imageFile
     * @param uuid
     * @return
     */
    public static void  compressPicture(MultipartFile imageFile,String uuid,String subpath) throws IOException {
       String path=forePath+subpath+"/"+uuid+".jpg";
        Thumbnails.of(forePath+subpath+"/"+uuid+"-y.jpg")
                .scale(1f)
                .outputQuality(0.5f)
                .toFile(path);
    }

    public static String FileSave2( String stringFile,String uuid,String subpath) throws IOException {
       //创建目录
        File file1=new File(forePath+subpath);
        if(!file1.exists()){
            file1.mkdirs();
        }
        String path=forePath+subpath+"/"+uuid+"-y.jpg";

        try {
            File file = new File(path);
            OutputStream out = new FileOutputStream(file);
            byte[] data = Base64.getMimeDecoder().decode(stringFile);
            out.write(data);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }


    /**
     * 小程序 传图片保存
     * @param multipartFile
     * @param uuid
     * @param subpath
     * @return
     * @throws IOException
     */
    public static String FileSave3( MultipartFile multipartFile,String uuid,String subpath) throws IOException {
        //创建目录
        File file1=new File(forePath+subpath);
        if(!file1.exists()){
            file1.mkdirs();
        }
        String path=forePath+subpath+"/"+uuid+"-y.jpg";
        File file  =  new File(path);
        multipartFile.transferTo(file);
        return path;
    }



}
