/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.httpclient.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName: HttpClientProperties
 * @Decription: http配置
 * @Author: rubik
 * rubik create HttpClientProperties.java of 2022/3/4 11:00 下午
 */
@ConfigurationProperties(prefix = "smart.commons.http")
public class HttpClientProperties {

    /**
     * 是否启用
     */
    private Boolean enabled = Boolean.FALSE;

    /**
     * 是否立即发送数据，即关闭缓冲区
     */
    private Boolean tcpNoDelay = Boolean.TRUE;

    /**
     * socket关闭时，还没有释放端口时，其他进程是否可以立即重用端口
     */
    private Boolean socketReuseAddress = Boolean.TRUE;

    /**
     * 关闭socket时，等待数据发送时间为多久。单位秒
     */
    private Integer socketLinger = 60;

    /**
     * 是否监听TCP链接是否有效
     */
    private Boolean socketKeepAlive = Boolean.TRUE;

    /**
     * 最大连接数
     */
    private Integer maxTotal = 256;

    /**
     * 最大路由数量
     */
    private Integer maxPerRoute = 128;

    /**
     * 最大空闲时间
     */
    private Long connectionIdleTimeoutSec = 600L;

    /**
     * 链接超时时间
     */
    private Integer connectionTimeoutMillis = 1000 * 3;

    /**
     * 读取超时时间
     */
    private Integer socketTimeoutMillis = 1000 * 10;

    /**
     * 从池子中获取连接超时时间
     */
    private Integer connectionRequestTimeoutMillis = 1000 * 10;

    /**
     * 重定向最大次数
     */
    private Integer maxRedirects = 3;

    /**
     * 是否自动处理身份验证
     */
    private Boolean authenticationEnabled = Boolean.TRUE;

    /**
     * 默认重试次数
     */
    private Integer retryCount = 3;

    /**
     * 请求是否自动重试
     */
    private Boolean requestSendRetryEnabled = Boolean.TRUE;

    /**
     * 请求代理，如： http://192.168.1.1:7890
     * 类型，地址，端口都不可少
     */
    private String proxyAddress;

    /**
     * 客户端标识
     */
    private String userAgent;

    /**
     * 最大返回结果数量
     */
    private Long maxResponseBytes = 1024L * 1024 * 10;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getTcpNoDelay() {
        return tcpNoDelay;
    }

    public void setTcpNoDelay(Boolean tcpNoDelay) {
        this.tcpNoDelay = tcpNoDelay;
    }

    public Boolean getSocketReuseAddress() {
        return socketReuseAddress;
    }

    public void setSocketReuseAddress(Boolean socketReuseAddress) {
        this.socketReuseAddress = socketReuseAddress;
    }

    public Integer getSocketLinger() {
        return socketLinger;
    }

    public void setSocketLinger(Integer socketLinger) {
        this.socketLinger = socketLinger;
    }

    public Boolean getSocketKeepAlive() {
        return socketKeepAlive;
    }

    public void setSocketKeepAlive(Boolean socketKeepAlive) {
        this.socketKeepAlive = socketKeepAlive;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxPerRoute() {
        return maxPerRoute;
    }

    public void setMaxPerRoute(Integer maxPerRoute) {
        this.maxPerRoute = maxPerRoute;
    }

    public Long getConnectionIdleTimeoutSec() {
        return connectionIdleTimeoutSec;
    }

    public void setConnectionIdleTimeoutSec(Long connectionIdleTimeoutSec) {
        this.connectionIdleTimeoutSec = connectionIdleTimeoutSec;
    }

    public Integer getConnectionTimeoutMillis() {
        return connectionTimeoutMillis;
    }

    public void setConnectionTimeoutMillis(Integer connectionTimeoutMillis) {
        this.connectionTimeoutMillis = connectionTimeoutMillis;
    }

    public Integer getSocketTimeoutMillis() {
        return socketTimeoutMillis;
    }

    public void setSocketTimeoutMillis(Integer socketTimeoutMillis) {
        this.socketTimeoutMillis = socketTimeoutMillis;
    }

    public Integer getConnectionRequestTimeoutMillis() {
        return connectionRequestTimeoutMillis;
    }

    public void setConnectionRequestTimeoutMillis(Integer connectionRequestTimeoutMillis) {
        this.connectionRequestTimeoutMillis = connectionRequestTimeoutMillis;
    }

    public Integer getMaxRedirects() {
        return maxRedirects;
    }

    public void setMaxRedirects(Integer maxRedirects) {
        this.maxRedirects = maxRedirects;
    }

    public Boolean getAuthenticationEnabled() {
        return authenticationEnabled;
    }

    public void setAuthenticationEnabled(Boolean authenticationEnabled) {
        this.authenticationEnabled = authenticationEnabled;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public Boolean getRequestSendRetryEnabled() {
        return requestSendRetryEnabled;
    }

    public void setRequestSendRetryEnabled(Boolean requestSendRetryEnabled) {
        this.requestSendRetryEnabled = requestSendRetryEnabled;
    }

    public String getProxyAddress() {
        return proxyAddress;
    }

    public void setProxyAddress(String proxyAddress) {
        this.proxyAddress = proxyAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Long getMaxResponseBytes() {
        return maxResponseBytes;
    }

    public void setMaxResponseBytes(Long maxResponseBytes) {
        this.maxResponseBytes = maxResponseBytes;
    }
}
