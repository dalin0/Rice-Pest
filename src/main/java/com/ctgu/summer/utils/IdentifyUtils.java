package com.ctgu.summer.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @ClassName IdentifyUtils
 * @Description: 图像识别类
 * @Author: XuZhen
 * @Create: 2021-07-20 10:14
 **/
public class IdentifyUtils {
    // 接收python脚本的输出结果
    int result;


    public static String identifyPic(String name){
        // 若Python脚本在windows主机中
        String cmdStr_windows = "python src/main/resources/script/text.py"+" " + name;
        // 若Python脚本在Linux主机中
        //String cmdStr_linux = "python /home/pythonCode/demo.py"+ " " + name;
        // 定义缓冲区、正常结果输出流、错误信息输出流
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ByteArrayOutputStream outerrStream = new ByteArrayOutputStream();

        Process proc = null;
        String res="";

        try {
//            String[] arg = new String[]{"python3", "src/main/resources/script/text.py"};
//            proc = Runtime.getRuntime().exec(arg);
            proc = Runtime.getRuntime().exec(cmdStr_windows);
            InputStream errStream = proc.getErrorStream();
            InputStream stream = proc.getInputStream();

            // 流读取与写入
            int len = -1;
            while ((len = errStream.read(buffer)) != -1) {
                outerrStream.write(buffer, 0, len);
            }
            while ((len = stream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            proc.waitFor();// 等待命令执行完成

            // 打印流信息
            System.out.println(outStream.toString());
            System.out.println(outerrStream.toString());

            // 将接收的输出结果转换为目标类型
            res = outStream.toString();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return res;

    }

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
