/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.httpclient.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geek45.lang.CollectionUtils;
import com.geek45.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName: DingMessage
 * @Decription: 钉钉消息
 * @Author: rubik
 * rubik create DingMessage.java of 2022/3/5 8:46 上午
 */
public class DingMessage {
    /**
     * 普通文本消息
     */
    public static final String TEXT = "text";

    /**
     * link消息
     */
    public static final String LINK = "link";
    /**
     * markdown消息
     */
    public static final String MARKDOWN = "markdown";
    /**
     * 卡片消息
     */
    public static final String ACTION_CARD = "actionCard";
    /**
     * 横向排列按钮
     */
    public static final Integer BTN_TRANSVERSE_TYPE = 1;
    /**
     * 竖直排列按钮
     */
    public static final Integer BTN_VERTICAL_TYPE = 0;

    /**
     * 消息内容
     */
    private String content;
    /**
     * 消息类型
     */
    private String msgType;
    /**
     * 是否at所有人
     */
    private Boolean atAll = Boolean.FALSE;
    /**
     * at用户手机号
     */
    private List<String> atMobiles;
    /**
     * 消息标题
     */
    private String title;
    /**
     * 图片链接
     */
    private String picUrl;
    /**
     * 消息链接
     */
    private String msgUrl;
    /**
     * 按钮类型：横向排列或者竖直排列
     */
    private Integer btnType;
    /**
     * 按钮列表
     */
    private List<Btn> btnList;

    public String getContent() {
        return content;
    }

    public String getMsgType() {
        return msgType;
    }

    public Boolean getAtAll() {
        return atAll;
    }

    public List<String> getAtMobiles() {
        return atMobiles;
    }

    public String getTitle() {
        return title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getMsgUrl() {
        return msgUrl;
    }

    public Integer getBtnType() {
        return btnType;
    }

    public List<Btn> getBtnList() {
        return btnList;
    }

    private DingMessage() {

    }

    /**
     * 根据手机号at用户，仅能在普通文本消息下使用
     * @param phone
     * @return
     */
    public DingMessage at(String... phone) {
        if (!StringUtils.equalsAny(msgType, TEXT)) {
            throw new IllegalArgumentException("该类型的消息无法at用户，请使用text类型的再进行at操作");
        }
        atMobiles.addAll(CollectionUtils.newArrayList(phone));
        this.atAll = Boolean.FALSE;
        return this;
    }

    /**
     * 根据手机号at用户，仅能在普通文本消息下使用
     * @param phones
     * @return
     */
    public DingMessage at(List<String> phones) {
        if (!StringUtils.equalsAny(msgType, TEXT)) {
            throw new IllegalArgumentException("该类型的消息无法at用户，请使用text类型的再进行at操作");
        }
        atMobiles.addAll(phones);
        this.atAll = Boolean.FALSE;
        return this;
    }

    /**
     * at所有人，仅能在普通文本消息下使用
     * @return
     */
    public DingMessage atAll() {
        if (!StringUtils.equalsAny(msgType, TEXT)) {
            throw new IllegalArgumentException("该类型的消息无法at用户，请使用text类型的再进行at操作");
        }
        atMobiles.clear();
        this.atAll = Boolean.TRUE;
        return this;
    }

    /**
     * 添加按钮，仅能在卡片消息下使用
     * @param btnTitle
     * @param btnUrl
     * @return
     */
    public DingMessage addBtn(String btnTitle, String btnUrl) {
        if (null == btnList) {
            throw new IllegalArgumentException("当前不是卡片消息，无法添加按钮");
        }
        this.btnList.add(Btn.create(btnTitle, btnUrl));
        return this;
    }

    /**
     * 创建卡片消息
     * @param title
     * @param message
     * @param btnType
     * @return
     */
    public static DingMessage createActionCardMessage(String title, String message, Integer btnType) {
        DingMessage msg = new DingMessage();
        msg.msgType = ACTION_CARD;
        msg.title = title;
        msg.content = message;
        msg.btnType = btnType;
        msg.btnList = new ArrayList<>();
        return msg;
    }

    /**
     * 创建卡片消息
     * @param title
     * @param message
     * @return
     */
    public static DingMessage createActionCardMessage(String title, String message) {
        return DingMessage.createActionCardMessage(title, message, BTN_VERTICAL_TYPE);
    }

    /**
     * 创建卡片消息
     * @param title     消息提示标题
     * @param message   消息内容
     * @param btnType   按钮类型
     * @param btnTitle  按钮显示内容
     * @param btnUrl    按钮点击后的url
     * @return
     */
    public static DingMessage createActionCardSingleBtnMessage(String title, String message, Integer btnType, String btnTitle, String btnUrl) {
        DingMessage msg = DingMessage.createActionCardMessage(title, message, btnType);
        msg.btnList.add(Btn.create(btnTitle, btnUrl));
        return msg;
    }

    /**
     * 创建markdown类型消息
     * @param title     提示标题
     * @param message   消息内容
     * @return
     */
    public static DingMessage createMarkdownMessage(String title, String message) {
        DingMessage msg = new DingMessage();
        msg.msgType = MARKDOWN;
        msg.title = title;
        msg.content = message;
        return msg;
    }

    /**
     * 创建link类型的消息
     * @param message       消息内容
     * @param title         消息提示标题
     * @param picUrl        图片链接
     * @param messageUrl    消息链接
     * @return
     */
    public static DingMessage createLinkMessage(String message, String title, String picUrl, String messageUrl) {
        DingMessage msg = new DingMessage();
        msg.msgType = LINK;
        msg.content = message;
        msg.title = title;
        msg.picUrl = picUrl;
        msg.msgUrl = messageUrl;
        return msg;
    }

    /**
     * 创建普通文本消息
     * @param message
     * @return
     */
    public static DingMessage createTextMessage(String message) {
        DingMessage msg = new DingMessage();
        msg.msgType = TEXT;
        msg.content = message;
        msg.atMobiles = new ArrayList<>();
        return msg;
    }

    /**
     * 将消息体构建为json
     * @return
     */
    public String build() {
        JSONObject object = new JSONObject();
        object.put("at", buildAt());
        object.put("msgtype", msgType);
        object.put("text", buildText());
        object.put("link", buildLink());
        object.put("markdown", buildMarkdown());
        object.put("actionCard", buildActionCard());
        return object.toJSONString();
    }

    /**
     * 构建卡片消息
     * @return
     */
    private JSONObject buildActionCard() {
        if (null == btnList) {
            return null;
        }
        JSONObject object = new JSONObject();
        object.put("title", title);
        object.put("text", content);
        object.put("btnOrientation", btnType);
        if (btnList.size() == 1) {
            Btn btn = btnList.get(0);
            object.put("singleTitle", btn.getBtnTitle());
            object.put("singleURL", btn.getBtnUrl());
            return object;
        }
        JSONArray array = new JSONArray();
        btnList.forEach(x -> {
            JSONObject btn = new JSONObject();
            btn.put("title", x.getBtnTitle());
            btn.put("actionURL", x.getBtnUrl());
            array.add(btn);
        });
        object.put("btns", array);
        return object;
    }

    /**
     * 构建markdown消息
     * @return
     */
    private JSONObject buildMarkdown() {
        JSONObject markdown = new JSONObject();
        markdown.put("title", title);
        markdown.put("text", content);
        return markdown;
    }

    /**
     * 构建link消息
     * @return
     */
    private JSONObject buildLink() {
        JSONObject link = new JSONObject();
        link.put("text", content);
        link.put("title", title);
        link.put("picUrl", picUrl);
        link.put("messageUrl", msgUrl);
        return link;
    }

    /**
     * 构建普通文本消息
     * @return
     */
    private JSONObject buildText() {
        JSONObject text = new JSONObject();
        text.put("content", content);
        return text;
    }

    /**
     * 构建at内容
     * @return
     */
    private JSONObject buildAt() {
        JSONObject at = new JSONObject();
        at.put("isAtAll", atAll);
        if (null == atMobiles || atMobiles.isEmpty()) {
            return at;
        }
        at.put("atMobiles", atMobiles);
        return at;
    }

    /**
     * 按钮
     */
    static class Btn {
        /**
         * 按钮跳转url
         */
        private String btnUrl;
        /**
         * 按钮展示内容
         */
        private String btnTitle;

        private Btn(String btnTitle, String btnUrl) {
            this.btnTitle = btnTitle;
            this.btnUrl = btnUrl;
        }

        static Btn create(String btnTitle, String btnUrl) {
            return new Btn(btnTitle, btnUrl);
        }

        public String getBtnUrl() {
            return btnUrl;
        }

        public void setBtnUrl(String btnUrl) {
            this.btnUrl = btnUrl;
        }

        public String getBtnTitle() {
            return btnTitle;
        }

        public void setBtnTitle(String btnTitle) {
            this.btnTitle = btnTitle;
        }
    }


}
