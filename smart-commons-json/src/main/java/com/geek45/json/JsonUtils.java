/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;

/**
 * @ClassName: JsonUtils
 * @Decription:
 * @Author: rubik
 *  rubik create JsonUtils.java of 2022/3/22 6:06 下午
 */
public class JsonUtils {

    /**
     * 范型反序列化
     *
     * @param serialization
     * @return
     */
    public static final <T> T deserialization(String serialization, TypeReference<T> typeReference) {
        return JSON.parseObject(serialization, typeReference.getType(), ParserConfig.global, JSON.DEFAULT_PARSER_FEATURE);
    }

    /**
     * 序列化
     *
     * @param obj
     * @return
     */
    public static final String serialization(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * 反序列化
     *
     * @param serialization
     * @param clazz
     * @param <P>
     * @return
     */
    public static final <P> P deserialization(String serialization, Class<P> clazz) {
        return JSON.parseObject(serialization, clazz);
    }

}