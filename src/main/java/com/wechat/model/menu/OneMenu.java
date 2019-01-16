package com.wechat.model.menu;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wechat.constant.Constant;
import com.wechat.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rongyaowen
 * on 2019/1/16.
 * 一级菜单按钮
 */
public class OneMenu {
    private List<BaseButton> button = new ArrayList<>();

    public OneMenu() {
    }

    public List<BaseButton> getButton() {
        return button;
    }

    public void setButton(List<BaseButton> button) {
        this.button = button;
    }

    public static void main(String[] args) {
        // 今日歌曲
        BaseButton musicBtn = new BaseButton("今日歌曲", "click", "V1001_TODAY_MUSIC", null);

        // 搜索
        BaseButton searchBtn = new BaseButton("搜索", "view", null, "https://www.baidu.com");
        // NBA
        BaseButton nbaBtn = new BaseButton("NBA", "view", null, "https://www.hupu.com");
        TwoMenu twoMenu1 = new TwoMenu("菜单");
        twoMenu1.getBaseButtons().add(searchBtn);
        twoMenu1.getBaseButtons().add(nbaBtn);

        // 静默授权
        String baseUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constant.WechatAccount.APPID + "&redirect_uri=http://h5d2fa.natappfree.cc" +
                "/wechat/authorization/snsApiBase&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
        System.out.println(baseUrl);
        BaseButton baseBtn = new BaseButton("静默授权", "view", null, baseUrl);
        // 用户授权
        String userInfoUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constant.WechatAccount.APPID + "&redirect_uri=http://h5d2fa.natappfree.cc" +
                "/wechat/authorization/snsApiUserInfo&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        System.out.println(userInfoUrl);
        BaseButton userInfoBtn = new BaseButton("用户授权", "view", null, userInfoUrl);
        TwoMenu twoMenu2 = new TwoMenu("授权");
        twoMenu2.getBaseButtons().add(baseBtn);
        twoMenu2.getBaseButtons().add(userInfoBtn);

        OneMenu menu = new OneMenu();
        menu.getButton().add(musicBtn);
        menu.getButton().add(twoMenu1);
        menu.getButton().add(twoMenu2);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        System.out.println(gson.toJson(menu));

    }
}
