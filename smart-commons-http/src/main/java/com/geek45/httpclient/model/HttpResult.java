/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.httpclient.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName: HttpResult
 * @Decription:
 * @Author: rubik
 * rubik create HttpResult.java of 2022/3/5 12:01 上午
 */
public class HttpResult<T> {

    private List<Cookie> cookies;
    private Integer status;
    private T resultObj;
    private String result;
    private Boolean success;
    private Throwable throwable;

    public static <T> HttpResult<T> success(HttpResponse response, CookieStore cookieStore, String character) throws IOException {
        HttpResult<T> result = new HttpResult<>();
        result.setStatus(response.getStatusLine().getStatusCode());
        result.setSuccess(Boolean.TRUE);
        result.setCookies(cookieStore.getCookies());
        result.setResult(EntityUtils.toString(response.getEntity(), character));
        return result;
    }

    public static <T> HttpResult<T> fail(Throwable throwable) {
        HttpResult<T> result = new HttpResult<>();
        result.setSuccess(Boolean.FALSE);
        result.setThrowable(throwable);
        result.setStatus(500);
        return result;
    }

    public List<Cookie> getCookies() {
        return cookies;
    }

    public void setCookies(List<Cookie> cookies) {
        this.cookies = cookies;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getResultObj() {
        if (StringUtils.isNotBlank(result)) {
            resultObj = JSON.parseObject(result, new TypeReference<T>() {});
        }
        return resultObj;
    }

    public void setResultObj(T resultObj) {
        this.resultObj = resultObj;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
