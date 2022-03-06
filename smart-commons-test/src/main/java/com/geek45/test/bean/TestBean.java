/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.test.bean;

import com.geek45.httpclient.client.DingClient;
import com.geek45.httpclient.client.HttpClientFactory;
import com.geek45.httpclient.client.RestHttpClient;
import com.geek45.httpclient.properties.DingProperties;
import com.geek45.httpclient.properties.HttpClientProperties;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: Testbean
 * @Decription:
 * @Author: rubik
 *  rubik create Testbean.java of 2022/3/5 11:02 上午
 */
@Configuration
public class TestBean {

    @Resource
    private HttpClientProperties httpClientProperties;
    @Resource
    private DingProperties dingProperties;

    @Bean
    public CloseableHttpClient httpClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return HttpClientFactory.createRestHttpClient(httpClientProperties);
    }

    @Bean
    public RestHttpClient restHttpClient(@Autowired HttpClient httpClient) {
        return new RestHttpClient(httpClient);
    }

    @Bean
    public DingClient dingClient(@Autowired RestHttpClient restHttpClient) {
        return new DingClient(restHttpClient, dingProperties);
    }

}
