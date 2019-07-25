package com.yiban.suoai;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {
    public static void main(String[] args) {
    Base64Util.imageTobyte("http://tmp/wxa89746c53ec389f1.o6zAJszUrvR4HctQ2YdoL9vkXpDM.8En2iFJbnWNMd37a60563d42854d53f0b991ede4963c");

    }
    public static byte[] getImageFromNetByUrl(String strUrl) {

        try {

            URL url = new URL(strUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            conn.setConnectTimeout(5 * 1000);

            InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据

            byte[] btImg = readInputStream(inStream);// 得到图片的二进制数据

            return btImg;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }


    /**

     * 从输入流中获取数据

     *

     * @param inStream

     *            输入流

     * @return

     * @throws Exception

     */

    public static byte[] readInputStream(InputStream inStream) throws Exception {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        byte[] buffer = new byte[10240];

        int len = 0;

        while ((len = inStream.read(buffer)) != -1) {

            outStream.write(buffer, 0, len);

        }

        inStream.close();

        return outStream.toByteArray();

    }



    public static String uploadQianURL(String fileUrl) {
        //获取文件名，文件名实际上在URL中可以找到
        //String fileName = fileUrl.substring(fileUrl.lastIndexOf("/"), fileUrl.indexOf("?")) + ".jpg";
        String fileName = fileUrl;
        //这里服务器上要将此图保存的路径
        String savePath = "D:/image";
        try {
            URL url = new URL(fileUrl);/*将网络资源地址传给,即赋值给url*/
            /*此为联系获得网络资源的固定格式用法，以便后面的in变量获得url截取网络资源的输入流*/
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            DataInputStream in = new DataInputStream(connection.getInputStream());
            /*此处也可用BufferedInputStream与BufferedOutputStream*/
            DataOutputStream out = new DataOutputStream(new FileOutputStream(savePath+fileName));
            /*将参数savePath，即将截取的图片的存储在本地地址赋值给out输出流所指定的地址*/
            byte[] buffer = new byte[4096];
            int count = 0;
            /*将输入流以字节的形式读取并写入buffer中*/
            while ((count = in.read(buffer)) > 0) {
                out.write(buffer, 0, count);
            }
            out.close();/*后面三行为关闭输入输出流以及网络资源的固定格式*/
            in.close();
            connection.disconnect();
            //返回内容是保存后的完整的URL
            return "1";/*网络资源截取并存储本地成功返回true*/

        } catch (Exception e) {
            System.out.println(e + fileUrl + savePath);
            return null;
        }
    }


}
