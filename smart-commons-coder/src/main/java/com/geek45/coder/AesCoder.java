/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.coder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @ClassName: AesCoder
 * @Decription:
 * @Author: rubik
 *  rubik create AesCoder.java of 2022/3/22 6:23 下午
 */
public class AesCoder {
    private static final String AES_CBC = "AES/CBC/PKCS5Padding";
    private static final String HEX = "0123456789ABCDEF";
    private static final String MODE = "AES";

    /**
     * 加密
     * @param data
     * @param key
     * @param initVector
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     */
    public static String aesEncode(byte[] data, byte[] key, byte[] initVector) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        IvParameterSpec iv = new IvParameterSpec(initVector);
        SecretKeySpec sKeySpec = new SecretKeySpec(key, MODE);
        Cipher cipher = Cipher.getInstance(AES_CBC);
        cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
        byte[] result = cipher.doFinal(data);
        return Base64.getMimeEncoder().encodeToString(result);
    }

    public static String aesDecode(String data, byte[] key, byte[] initVector) throws IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException {
        IvParameterSpec iv = new IvParameterSpec(initVector);
        SecretKeySpec sKeySpec = new SecretKeySpec(key, MODE);
        // 解密
        Cipher cipher = Cipher.getInstance(AES_CBC);
        cipher.init(Cipher.DECRYPT_MODE, sKeySpec, iv);
        byte[] b = Base64.getMimeDecoder().decode(data);
        byte[] result = cipher.doFinal(b);
        return new String(result);
    }

    /**
     * 将byte转换为16进制字符串
     * @param src
     * @return
     */
    public static String byteToHexString(byte[] src) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xff;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                sb.append("0");
            }
            sb.append(hv);
        }
        return sb.toString();
    }

    /**
     * 将16进制字符串装换为byte数组
     * @param hexString
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] b = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            b[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return b;
    }

    private static byte charToByte(char c) {
        return (byte) HEX.indexOf(c);
    }

}