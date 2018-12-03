package com.wangyuxuan.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @Auther: wangyuxuan
 * @Date: 2018/12/3 17:02
 * @Description:
 */
public class MD5Util {

    public static String md5(String str, String salt){
        return new Md5Hash(str, salt).toString();
    }

    public static void main(String[] args) {
        String md5 = md5("abc123","wangyuxuan") ;
        System.out.println(md5);
    }
}
