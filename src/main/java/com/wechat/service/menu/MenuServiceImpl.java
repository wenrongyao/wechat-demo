package com.wechat.service.menu;

import com.wechat.constant.Constant;
import com.wechat.util.HttpRequest;
import org.springframework.stereotype.Service;

/**
 * Created by rongyaowen
 * on 2019/1/16.
 */
@Service
public class MenuServiceImpl implements IMenuService {
    private static final String GET_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token={ACCESS_TOKEN}";
    private static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token={ACCESS_TOKEN}";
    private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token={ACCESS_TOKEN}";

    @Override
    public String getMenus() {
        String url = GET_MENU_URL.replace("{ACCESS_TOKEN}", Constant.ACCESS_TOKEN);
        String jsonStr = HttpRequest.get(url, null, false);
        return jsonStr;
    }

    @Override
    public String deleteMenus() {
        String url = DELETE_MENU_URL.replace("{ACCESS_TOKEN}", Constant.ACCESS_TOKEN);
        String jsonStr = HttpRequest.get(url, null, false);
        return jsonStr;
    }

    @Override
    public String addMenus(String menuStr) {
        String url = CREATE_MENU_URL.replace("{ACCESS_TOKEN}", Constant.ACCESS_TOKEN);
        String jsonStr = HttpRequest.post(url, menuStr, null, Constant.ContentType.APPLICATION_JSON, false);
        return jsonStr;
    }
}
