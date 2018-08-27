package com.yaochow.user.util;

import org.springframework.util.DigestUtils;

public class MD5Util {

    public static String md5(String text, String key) throws Exception {
        //加密后的字符串
        String encodeStr = DigestUtils.md5DigestAsHex((text + key).getBytes());
        return encodeStr;
    }

    public static boolean verify(String text, String key, String md5) throws Exception {
        //根据传入的密钥进行验证
        String md5Text = md5(text, key);
        if (md5Text.equalsIgnoreCase(md5)) {
            return true;
        }

        return false;
    }
}