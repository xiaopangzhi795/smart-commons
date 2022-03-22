/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.coder;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: Md5Coder
 * @Decription:
 * @Author: rubik
 *  rubik create Md5Coder.java of 2022/3/22 6:25 下午
 */
public class Md5Coder {

    public static String md5(String data) {
        byte[] digest = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest  = md5.digest(data.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //16是表示转换为16进制数
        String md5Str = new BigInteger(1, digest).toString(16);
        return md5Str;
    }

}
