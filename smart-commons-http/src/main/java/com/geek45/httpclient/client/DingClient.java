/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.httpclient.client;

import com.alibaba.fastjson.JSON;
import com.geek45.httpclient.properties.DingProperties;
import com.geek45.httpclient.constant.HttpConstant;
import com.geek45.httpclient.model.DingMessage;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: DingClient
 * @Decription:
 * @Author: rubik
 *  rubik create DingClient.java of 2022/3/5 9:39 上午
 */
public class DingClient {
    private static final Logger logger = LoggerFactory.getLogger(DingClient.class);
    private RestHttpClient restHttpClient;
    private DingProperties dingProperties;

    public DingClient(RestHttpClient restHttpClient, DingProperties dingProperties) {
        this.restHttpClient = restHttpClient;
        this.dingProperties = dingProperties;
    }

    /**
     * 发送消息
     * @param url
     * @param message
     */
    public void send(String url, DingMessage message) {
        logger.info("send message is:{}", JSON.toJSONString(message));
        restHttpClient.doPost(url, null, message.build().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 发送消息
     * @param message
     */
    public void send(DingMessage message) {
        if (StringUtils.isAnyBlank(dingProperties.getWebhook())) {
            throw new IllegalArgumentException("请配置webhook");
        }
        send(dingProperties.getWebhook(), message);
    }

    /**
     * 根据签名发送消息
     *
     * @param message
     */
    public void sendBySign(DingMessage message) {
        if (StringUtils.isAnyBlank(dingProperties.getSign(), dingProperties.getWebhook())) {
            throw new IllegalArgumentException("请配置webhook和sign");
        }
        send((StringUtils.join(dingProperties.getWebhook(), "&", generatorSignStr(dingProperties.getSign()))), message);
    }

    private String generatorSignStr(String sign) {
        try {
            Long timestamp = System.currentTimeMillis();
            String stringToSign = StringUtils.join(timestamp, "\n", sign);
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(sign.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
            String signStr = URLEncoder.encode(new String(Base64.encodeBase64(signData)), HttpConstant.DEFAULT_CHARACTER);
            return StringUtils.join("timestamp=", timestamp, "&sign=", signStr);
        } catch (UnsupportedEncodingException e) {
            logger.error("generatorSignStr exception..", e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("generatorSignStr exception..", e);
        } catch (InvalidKeyException e) {
            logger.error("generatorSignStr exception..", e);
        }
        return "timestamp=&sign=";
    }
}
