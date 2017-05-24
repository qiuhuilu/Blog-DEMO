package com.ctrip.blog.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by qiuhl on 2017/5/24.
 */
public class CryptographyUtil {
    /**
     * 使用Shiro中的md5加密
     * @param str
     * @param salt
     * @return
     */
    public static String md5(String str, String salt){
        //md5Hash是Shiro中的加密方法
        return new Md5Hash(str, salt).toString();
    }

    public static void main(String[] args) {
        String password = "qiuhl";
        System.out.println("MD5密码："+CryptographyUtil.md5(password,"ctrip"));
    }
}
