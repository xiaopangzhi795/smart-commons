/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: EnumUtils
 * @Decription:
 * @Author: rubik
 *  rubik create EnumUtils.java of 2022/3/22 5:57 下午
 */
public class EnumUtils {

    /**
     * 获取枚举
     * @param enumClass
     * @param enumName
     * @param <E>
     * @return
     */
    public static <E extends Enum<E>> E getEnum(final Class<E> enumClass, final String enumName) {
        return getEnum(enumClass, enumName, null);
    }

    /**
     * 获取枚举
     * @param enumClass
     * @param enumName
     * @param defaultEnum
     * @param <E>
     * @return
     */
    public static <E extends Enum<E>> E getEnum(final Class<E> enumClass, final String enumName, final E defaultEnum) {
        if (enumName == null) {
            return defaultEnum;
        }
        try {
            return Enum.valueOf(enumClass, enumName);
        } catch (final IllegalArgumentException ex) {
            return defaultEnum;
        }
    }

    /**
     * 获取枚举，忽略大小写
     * @param enumClass
     * @param enumName
     * @param <E>
     * @return
     */
    public static <E extends Enum<E>> E getEnumIgnoreCase(final Class<E> enumClass, final String enumName) {
        return getEnumIgnoreCase(enumClass, enumName, null);
    }

    /**
     * 获取枚举，忽略大小写
     * @param enumClass
     * @param enumName
     * @param defaultEnum
     * @param <E>
     * @return
     */
    public static <E extends Enum<E>> E getEnumIgnoreCase(final Class<E> enumClass, final String enumName, final E defaultEnum) {
        if (enumName == null || !enumClass.isEnum()) {
            return defaultEnum;
        }
        for (final E each : enumClass.getEnumConstants()) {
            if (each.name().equalsIgnoreCase(enumName)) {
                return each;
            }
        }
        return defaultEnum;
    }

    /**
     * 获取枚举中所有的枚举值
     * @param enumClass
     * @param <E>
     * @return
     */
    public static <E extends Enum<E>> List<E> getEnumList(final Class<E> enumClass) {
        return new ArrayList<>(Arrays.asList(enumClass.getEnumConstants()));
    }

    /**
     * 判断是否为需要的枚举
     * @param enumClass
     * @param enumName
     * @param <E>
     * @return
     */
    public static <E extends Enum<E>> boolean isValidEnum(final Class<E> enumClass, final String enumName) {
        return getEnum(enumClass, enumName) != null;
    }

    /**
     * 判断是否为需要的枚举，忽略大小写
     * @param enumClass
     * @param enumName
     * @param <E>
     * @return
     */
    public static <E extends Enum<E>> boolean isValidEnumIgnoreCase(final Class<E> enumClass, final String enumName) {
        return getEnumIgnoreCase(enumClass, enumName) != null;
    }
}
