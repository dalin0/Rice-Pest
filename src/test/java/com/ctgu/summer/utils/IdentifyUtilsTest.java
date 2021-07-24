package com.ctgu.summer.utils;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static jdk.nashorn.internal.objects.Global.println;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassName IdentifyUtilsTest
 * @Description:
 * @Author: XuZhen
 * @Create: 2021-07-20 11:08
 **/
class IdentifyUtilsTest {

//    public static void main(String[] args) {
//        byte[] btImg = getFileStream("https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E6%B0%B4%E7%A8%BB%E7%99%BD%E5%8F%B6%E6%9E%AF%E7%97%85&hs=0&pn=1&spn=0&di=51150&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=14322662%2C3197035316&os=3530176952%2C4193266466&simid=0%2C0&adpicid=0&lpn=0&ln=30&fr=ala&fm=&sme=&cg=&bdtype=0&oriquery=%E6%B0%B4%E7%A8%BB%E7%99%BD%E5%8F%B6%E6%9E%AF%E7%97%85&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%3A%2F%2Fwww.ferts.cn%2Ffile%2Fupload%2F202004%2F17%2F112921241.jpg%26refer%3Dhttp%3A%2F%2Fwww.ferts.cn%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1629429674%26t%3D29c1d4f61edb97105f960b02f1ed7779&fromurl=ippr_z2C%24qAzdH3FAzdH3Fz2xvo_z%26e3B562_z%26e3BvgAzdH3Frs7fAzdH3Fetjo_z%26e3Brir%3Fwt1%3D8mmn9da&gsm=1&islist=&querylist=");
//    }

    /**
     * 得到文件流
     * @param url  网络图片URL地址
     * @return
     */
    public static byte[] getFileStream(String url){
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
            byte[] btImg = readInputStream(inStream);//得到图片的二进制数据
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从输入流中获取数据
     * @param inStream 输入流
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}