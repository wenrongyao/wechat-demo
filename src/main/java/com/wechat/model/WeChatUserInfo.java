package com.wechat.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rongyaowen on 2018/9/10.
 */
public class WeChatUserInfo {
    @SerializedName("openid")
    private String openId;
    @SerializedName("nickname")
    private String nickName;
    private String sex;
    private String province;
    private String city;
    private String country;
    @SerializedName("headimgurl")
    private String headImgUrl;
    // 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
//    private String privilege;
    // 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
//    @SerializedName("unionid")
//    private String unionId;
    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

//    public String getPrivilege() {
//        return privilege;
//    }
//
//    public void setPrivilege(String privilege) {
//        this.privilege = privilege;
//    }
//
//    public String getUnionId() {
//        return unionId;
//    }
//
//    public void setUnionId(String unionId) {
//        this.unionId = unionId;
//    }

    @Override
    public String toString() {
        return "openId='" + openId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex='" + sex + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", headImgUrl='" + headImgUrl + '\'';
    }
}
