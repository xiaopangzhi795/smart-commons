/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.test.http;

import com.geek45.httpclient.client.DingClient;
import com.geek45.httpclient.client.RestHttpClient;
import com.geek45.httpclient.model.DingMessage;
import com.geek45.httpclient.model.HttpResult;
import com.geek45.httpclient.properties.DingProperties;
import com.geek45.test.SmartCommonsTestApplicationTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @ClassName: HttpClientTest
 * @Decription:
 * @Author: rubik
 * rubik create HttpClientTest.java of 2022/3/5 9:53 上午
 */
@DisplayName("httpclient相关测试")
public class HttpClientTest extends SmartCommonsTestApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientTest.class);
    @Autowired
    private RestHttpClient restHttpClient;
    @Autowired
    private DingClient dingClient;
    @Resource
    private DingProperties dingProperties;

    @DisplayName("获取网络图片")
    @Test
    public void testGetImage() throws IOException {
        String imagesUrl = "https://img-home.csdnimg.cn/images/20201124032511.png";
        BufferedImage bufferedImage = restHttpClient.doGetImage(imagesUrl);
        File file = new File("/Users/qian/temp/test.png");
        ImageIO.write(bufferedImage, "png", file);
    }
    @Test
    @DisplayName("测试httpClient")
    public void testHttpClient() {
        HttpResult<String> result = restHttpClient.doGet("https://www.baidu.com", null);
        logger.info("result is: {}", result.getResult());
    }

    @DisplayName("测试发送钉钉消息")
    @Test
    public void testSendDing() {
        DingMessage message = DingMessage.createTextMessage("hello");
        message.at(dingProperties.getMobile());
        dingClient.sendBySign(message);
    }
}
