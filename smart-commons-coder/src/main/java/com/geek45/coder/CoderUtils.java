/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.coder;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @ClassName: CoderUtils
 * @Decription:
 * @Author: rubik
 *  rubik create CoderUtils.java of 2022/3/22 6:31 下午
 */
public class CoderUtils {
    /**
     * 使用公钥对字符串进行加密
     * @param data
     * @param publicKey
     * @return
     */
    public static String rsaEncodeByPubKey(String data, String publicKey) {
        byte[] key = Base64.getMimeDecoder().decode(publicKey);
        try {
            byte[] encode = RsaCoder.encryptByPublicKey(data.getBytes(), key);
            return Base64.getMimeEncoder().encodeToString(encode);
        } catch (Exception e) {
            e.printStackTrace();
            return "加密失败:" + e.getLocalizedMessage();
        }
    }

    /**
     * 使用公钥对字符串进行加密
     *
     * @param data
     * @param privateKey
     * @return
     */
    public static String rsaEncodeByPriKey(String data, String privateKey) {
        byte[] key = Base64.getMimeDecoder().decode(privateKey);
        try {
            byte[] encode = RsaCoder.encryptByPrivateKey(data.getBytes(), key);
            return Base64.getMimeEncoder().encodeToString(encode);
        } catch (Exception e) {
            e.printStackTrace();
            return "加密失败:" + e.getLocalizedMessage();
        }
    }


    /**
     * 根据私钥解密
     *
     * @param enData
     * @param privateKey
     * @return
     */
    public static String rsaDecodeByPriKey(String enData, String privateKey) {
        byte[] key = Base64.getMimeDecoder().decode(privateKey);
        byte[] encode = Base64.getMimeDecoder().decode(enData);
        try {
            byte[] decode = RsaCoder.decryptByPrivateKey(encode, key);
            return new String(decode);
        } catch (Exception e) {
            e.printStackTrace();
            return "解密失败:" + e.getLocalizedMessage();
        }

    }

    /**
     * 根据公钥解密
     *
     * @param enData
     * @param publicKey
     * @return
     */
    public static String rsaDecodeByPubKey(String enData, String publicKey) {
        byte[] key = Base64.getMimeDecoder().decode(publicKey);
        byte[] encode = Base64.getMimeDecoder().decode(enData);
        try {
            byte[] decode = RsaCoder.decryptByPublicKey(encode, key);
            return new String(decode);
        } catch (Exception e) {
            e.printStackTrace();
            return "解密失败:" + e.getLocalizedMessage();
        }
    }

    /**
     * aes 加密
     *
     * @param data
     * @param key
     * @param iv
     * @return
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws BadPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String aesEncode(String data, String key, String iv) throws InvalidAlgorithmParameterException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        return AesCoder.aesEncode(data.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8), iv.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * AES 解密
     *
     * @param data
     * @param key
     * @param iv
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     */
    public static String aesDecode(String data, String key, String iv) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        return AesCoder.aesDecode(data, key.getBytes(StandardCharsets.UTF_8), iv.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * hmac 加密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String hmac(String data, String key) throws Exception {
        return Base64.getMimeEncoder().encodeToString(HmacCoder.encryptHMAC(data.getBytes(), key));
    }

    /**
     * md5 转16位
     * @param data
     * @return
     */
    public static String md5Convert16(String data) {
        return Md5Coder.md5(data).substring(8, 24);
    }

    /**
     * md5 加密
     * @param data
     * @return
     */
    public static String md5(String data) {
        return Md5Coder.md5(data);
    }

    /**
     * sha256 加密
     * @param data
     * @return
     */
    public static String sha256(String data) {
        return ShaCoder.sha256(data);
    }
}