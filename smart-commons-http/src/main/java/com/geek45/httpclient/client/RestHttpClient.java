/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.httpclient.client;

import com.geek45.httpclient.constant.HttpConstant;
import com.geek45.httpclient.enums.HttpMethod;
import com.geek45.httpclient.model.HttpResult;
import com.geek45.lang.StringUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Map;


/**
 * @ClassName: RestHttpClient
 * @Decription:
 * @Author: rubik
 *  rubik create RestHttpClient.java of 2022/3/4 11:37 下午
 */
public class RestHttpClient {

    private static final Logger logger = LoggerFactory.getLogger(RestHttpClient.class);

    private HttpClient httpClient;

    public <T> HttpResult<T> doPost(String url, Map<String, String> urlParam, byte[] entity) {
        return doPost(url, urlParam, entity, null);
    }
    public <T> HttpResult<T> doPost(String url, Map<String, String> urlParam, byte[] entity, Map<String, String> headers) {
        return execute(HttpMethod.POST, url, urlParam, entity, headers, HttpConstant.DEFAULT_CHARACTER);
    }
    public <T> HttpResult<T> doGet(String url, Map<String, String> urlParam) {
        return doGet(url, urlParam, null);
    }

    public <T> HttpResult<T> doGet(String url, Map<String, String> urlParam, Map<String, String> headers) {
        return execute(HttpMethod.GET, url, urlParam, null, headers, HttpConstant.DEFAULT_CHARACTER);
    }

    public <T> HttpResult<T> execute(HttpMethod method, String url, Map<String, String> urlParam, byte[] entity, Map<String, String> headers, String character) {
        try {
            logger.info("method is:{}, url: {}", method.name(), url);
            if (null == method) {
                throw new IllegalArgumentException("method can not be null");
            }
            if (StringUtils.isBlank(url)) {
                throw new IllegalArgumentException("url can not be blank");
            }
            HttpUriRequest request = buildRequestByMethod(method);
            if (request instanceof HttpEntityEnclosingRequestBase) {
                HttpEntityEnclosingRequestBase req = (HttpEntityEnclosingRequestBase) request;
                if (null != entity && entity.length > 0) {
                    ContentType contentType = getContentType(character, null != headers ? headers.get(HttpConstant.CONTENT_TYPE_KEY) : null);
                    req.setEntity(new ByteArrayEntity(entity, contentType));
                }
                req.setURI(getUri(url, urlParam, character));
            } else if (request instanceof HttpRequestBase) {
                HttpRequestBase req = (HttpRequestBase) request;
                if (null != entity && entity.length > 0) {
                    throw new UnsupportedOperationException(String.format("entity not supported with http method '%s'", method.name()));
                }
                req.setURI(getUri(url, urlParam, character));
            }
            if (null != headers && !headers.isEmpty()) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    String k = entry.getKey();
                    String v = entry.getValue();
                    if (StringUtils.isAnyBlank(k, v)) {
                        continue;
                    }
                    request.addHeader(k, v);
                }
            }

            CookieStore cookieStore = new BasicCookieStore();
            HttpContext localContext = getLocalContext(cookieStore);

            return HttpResult.success(httpClient.execute(request, localContext), cookieStore, character);
        } catch (Throwable throwable) {
            logger.error("http client execute exception...", throwable);
            return HttpResult.fail(throwable);
        }
    }

    public RestHttpClient(HttpClient httpClient) {
        if (null == httpClient) {
            throw new IllegalArgumentException("bad http client configuration");
        }
        this.httpClient = httpClient;
    }

    /**
     * 初始化contentType
     * @param character
     * @param mimeType
     * @return
     */
    private ContentType getContentType(String character, String mimeType) {
        if (StringUtils.isNotBlank(mimeType)) {
            mimeType = mimeType.split(";")[0];
        } else {
            mimeType = ContentType.APPLICATION_JSON.getMimeType();
        }
        if (StringUtils.isNotBlank(character)) {
            return ContentType.create(mimeType, character);
        }
        if (StringUtils.equals(mimeType, ContentType.APPLICATION_FORM_URLENCODED.getMimeType())) {
            return ContentType.create(ContentType.APPLICATION_FORM_URLENCODED.getMimeType(), ContentType.APPLICATION_FORM_URLENCODED.getCharset());
        }
        if (StringUtils.equals(mimeType, ContentType.APPLICATION_JSON.getMimeType())) {
            return ContentType.create(ContentType.APPLICATION_JSON.getMimeType(), ContentType.APPLICATION_JSON.getCharset());
        }
        if (StringUtils.equals(mimeType, ContentType.APPLICATION_ATOM_XML.getMimeType())) {
            return ContentType.create(ContentType.APPLICATION_ATOM_XML.getMimeType(), ContentType.APPLICATION_ATOM_XML.getCharset());
        }
        if (StringUtils.equals(mimeType, ContentType.APPLICATION_OCTET_STREAM.getMimeType())) {
            return ContentType.create(ContentType.APPLICATION_OCTET_STREAM.getMimeType(), ContentType.APPLICATION_OCTET_STREAM.getCharset());
        }
        if (StringUtils.equals(mimeType, ContentType.APPLICATION_SVG_XML.getMimeType())) {
            return ContentType.create(ContentType.APPLICATION_SVG_XML.getMimeType(), ContentType.APPLICATION_SVG_XML.getCharset());
        }
        if (StringUtils.equals(mimeType, ContentType.APPLICATION_XHTML_XML.getMimeType())) {
            return ContentType.create(ContentType.APPLICATION_XHTML_XML.getMimeType(), ContentType.APPLICATION_XHTML_XML.getCharset());
        }
        if (StringUtils.equals(mimeType, ContentType.APPLICATION_XML.getMimeType())) {
            return ContentType.create(ContentType.APPLICATION_XML.getMimeType(), ContentType.APPLICATION_XML.getCharset());
        }
        return ContentType.create(mimeType, HttpConstant.DEFAULT_CHARACTER);
    }

    /**
     * 生成uri
     * @param url
     * @param queryParam
     * @param character
     * @return
     * @throws URISyntaxException
     */
    private URI getUri(String url, Map<String, String> queryParam, String character) throws URISyntaxException {
        URIBuilder builder = new URIBuilder(url);
        Charset charset = Charset.forName(character);
        builder.setCharset(charset);
        if (null == queryParam || queryParam.isEmpty()) {
            return builder.build();
        }
        for (Map.Entry<String, String> entry : queryParam.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (StringUtils.isAnyBlank(key, value)) {
                continue;
            }
            builder.addParameter(key, value);
        }
        return builder.build();
    }

    /**
     * 生成请求
     * @param method
     * @return
     */
    private HttpUriRequest buildRequestByMethod(HttpMethod method) {
        switch (method) {
            case GET:
                return new HttpGet();
            case HEAD:
                return new HttpHead();
            case POST:
                return new HttpPost();
            case PUT:
                return new HttpPut();
            case PATCH:
                return new HttpPatch();
            case DELETE:
                return new HttpDelete();
            case OPTIONS:
                return new HttpOptions();
            case TRACE:
                return new HttpTrace();
            default:
                throw new UnsupportedOperationException(String.format("http method %s not supported", method.name()));
        }
    }

    /**
     * 获取一个http的上下文对象，用来存储更新过的cookie
     * @param cookieStore
     * @return
     */
    private HttpContext getLocalContext(CookieStore cookieStore) {
        HttpContext localContext = new BasicHttpContext();
        localContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
        return localContext;
    }

}
