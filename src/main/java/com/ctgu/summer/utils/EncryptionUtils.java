package com.ctgu.summer.utils;

import static com.qcloud.cos.utils.Md5Utils.md5Hex;

/**
 * @ClassName EncryptionUtils
 * @Description TODO
 * @Author wby
 * @Data 2021/7/17 14:32
 * @Version 1.0
 */
public class EncryptionUtils {

    /**
     * @category 加盐MD5加密
     * @param password
     * @param Salt
     * @return
     */
    public static String getSaltMD5(String password,String Salt) {
        password = md5Hex(password + Salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = Salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return String.valueOf(cs);
    }

    /**
     * @category 判断密码是否一致
     * @param password 密码
     * @param md5str 加密过的密码
     * @return
     */
    public static boolean getSaltverifyMD5(String password,String md5str,String Salt) {
        return md5str.equals(getSaltMD5(password,Salt));
    }
}
