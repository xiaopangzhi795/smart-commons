/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.coder;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @ClassName: HmacCoder
 * @Decription:
 * @Author: rubik
 *  rubik create HmacCoder.java of 2022/3/22 6:25 下午
 */
public class HmacCoder {

    public static final String KEY_MAC = "HmacSHA256";


    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);
        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

        SecretKey secretKey = new SecretKeySpec(key.getBytes(), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
    }

    public static String encryptBASE64(byte[] key) throws Exception {
        return Base64.getMimeEncoder().encodeToString(key);
    }

}
