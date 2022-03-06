/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.httpclient.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName: DingProperties
 * @Decription:
 * @Author: rubik
 * rubik create DingProperties.java of 2022/3/5 9:55 上午
 */
@ConfigurationProperties(prefix = "smart.commons.ding")
public class DingProperties {

    /**
     * webhook
     */
    private String webhook;
    /**
     * at手机号
     */
    private String mobile;
    /**
     * 加签
     */
    private String sign;

    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
