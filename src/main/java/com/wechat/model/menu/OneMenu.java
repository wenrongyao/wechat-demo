package com.wechat.model.menu;


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
        TwoMenu twoMenu = new TwoMenu("菜单");
        twoMenu.getBaseButtons().add(searchBtn);
        twoMenu.getBaseButtons().add(nbaBtn);

        OneMenu menu = new OneMenu();
        menu.getButton().add(musicBtn);
        menu.getButton().add(twoMenu);
        System.out.println(GsonUtil.toJson(menu));

    }
}
