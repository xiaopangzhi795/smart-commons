/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.lang;

import java.util.Locale;
import java.util.UUID;

/**
 * @ClassName: UuidUtils
 * @Decription:
 * @Author: rubik
 *  rubik create UuidUtils.java of 2022/3/22 5:42 下午
 */
public class UuidUtils {

    public static final String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static final String uuidUpperCase() {
        return uuid().toUpperCase(Locale.ROOT);
    }

    public static final String uuid8() {
        return uuid().substring(8, 16);
    }

    public static final String uuid16() {
        return uuid().substring(8, 24);
    }
}
