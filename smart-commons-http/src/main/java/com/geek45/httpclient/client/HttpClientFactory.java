/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.httpclient.client;

import com.geek45.httpclient.constant.HttpConstant;
import com.geek45.httpclient.properties.HttpClientProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: HttpClientConfiguration
 * @Decription:
 * @Author: rubik
 * rubik create HttpClientConfiguration.java of 2022/3/4 11:23 下午
 */
public class HttpClientFactory {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientFactory.class);

    public static CloseableHttpClient createRestHttpClient(HttpClientProperties httpClientProperties) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        logger.info("init http client...type is :{}", CloseableHttpClient.class.getName());

        //socket
        SocketConfig socketConfig = SocketConfig.custom()
                .setTcpNoDelay(httpClientProperties.getTcpNoDelay())
                .setSoReuseAddress(httpClientProperties.getSocketReuseAddress())
                .setSoLinger(httpClientProperties.getSocketLinger())
                .setSoKeepAlive(httpClientProperties.getSocketKeepAlive())
                .build();

        //忽略服务器端ssl证书校验
        SSLContext sslContext = SSLContexts.custom()
                .loadTrustMaterial(new TrustAllStrategy())
                .build();

        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register(HttpConstant.HTTP, new PlainConnectionSocketFactory())
                .register(HttpConstant.HTTPS, sslConnectionSocketFactory)
                .build();

        //链接管理器
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        connectionManager.setMaxTotal(httpClientProperties.getMaxTotal());
        connectionManager.setDefaultMaxPerRoute(httpClientProperties.getMaxPerRoute());
        connectionManager.closeIdleConnections(httpClientProperties.getConnectionIdleTimeoutSec(), TimeUnit.SECONDS);
        connectionManager.setDefaultSocketConfig(socketConfig);

        //request
        RequestConfig.Builder requestBuild = RequestConfig.custom()
                .setConnectTimeout(httpClientProperties.getConnectionTimeoutMillis())
                .setSocketTimeout(httpClientProperties.getSocketTimeoutMillis())
                .setConnectionRequestTimeout(httpClientProperties.getConnectionRequestTimeoutMillis())
                .setMaxRedirects(httpClientProperties.getMaxRedirects())
                .setAuthenticationEnabled(httpClientProperties.getAuthenticationEnabled());

        //proxy
        if (StringUtils.isNotBlank(httpClientProperties.getProxyAddress())) {
            requestBuild.setProxy(HttpHost.create(httpClientProperties.getProxyAddress()));
        }

        //retry
        HttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler(httpClientProperties.getRetryCount(), httpClientProperties.getRequestSendRetryEnabled());

        //httpclient
        HttpClientBuilder builder = HttpClients.custom()
                .disableAuthCaching()
                .disableAutomaticRetries()
                .evictExpiredConnections()
                .setConnectionManager(connectionManager)
                .setRetryHandler(retryHandler)
                .setDefaultRequestConfig(requestBuild.build());

        if (StringUtils.isNotBlank(httpClientProperties.getUserAgent())) {
            builder.setUserAgent(httpClientProperties.getUserAgent());
        }

        return builder.build();
    }


}
