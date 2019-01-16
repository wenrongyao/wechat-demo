package com.wechat.model.menu;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * 二级菜单按钮
 */
public class TwoMenu extends BaseButton {
    @SerializedName("sub_button")
    private List<BaseButton> baseButtons = new ArrayList<>();

    public TwoMenu() {
    }

    public TwoMenu(String name) {
        super(name,null,null,null);
    }

    public List<BaseButton> getBaseButtons() {
        return baseButtons;
    }

    public void setBaseButtons(List<BaseButton> baseButtons) {
        this.baseButtons = baseButtons;
    }
}