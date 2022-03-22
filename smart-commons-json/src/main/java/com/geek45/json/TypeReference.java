/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.json;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @ClassName: TypeReference
 * @Decription:
 * @Author: rubik
 *  rubik create TypeReference.java of 2022/3/22 6:06 下午
 */
public class TypeReference<T> {

    protected final Type type;

    protected TypeReference(){
        Type superClass = getClass().getGenericSuperclass();
        Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];

        this.type = type;
    }

    /**
     * Gets underlying {@code Type} instance.
     */
    public Type getType() {
        return type;
    }

}
