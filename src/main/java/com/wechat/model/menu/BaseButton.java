package com.wechat.model.menu;

import com.google.gson.annotations.SerializedName;

/**
 * 基础button
 * {
 * "type":"view",
 * "name":"搜索",
 * "url":"http://www.soso.com/"
 * },
 */
public class BaseButton {
    // 菜单标题，不超过16个字节，子菜单不超过60个字节
    private String name;
    // 菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
    private String type;
    // 菜单KEY值，用于消息接口推送，不超过128字节
    private String key;
    // 网页 链接，用户点击菜单可打开链接，不超过1024字节。 type为miniprogram时，不支持小程序的老版本客户端将打开本url。
    private String url;
    // 调用新增永久素材接口返回的合法media_id
    @SerializedName("media_id")
    private String mediaId;
    // 小程序的appid（仅认证公众号可配置）
    @SerializedName("appid")
    private String appId;
    // 小程序的页面路径
    @SerializedName("pagepath")
    private String pagePath;

    public BaseButton() {
    }

    public BaseButton(String name, String type, String key, String url) {
        this.name = name;
        this.type = type;
        this.key = key;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }
}