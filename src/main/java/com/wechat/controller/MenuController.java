package com.wechat.controller;

import com.wechat.service.menu.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rongyaowen
 * on 2019/1/16.
 * 菜单接口
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;

    /**
     * 获取所有菜单
     *
     * @return
     */
    @RequestMapping("/getMenus")
    public String getMenus() {
        return menuService.getMenus();
    }

    /**
     * 删除所有菜单
     *
     * @return
     */
    @RequestMapping("/deleteMenus")
    public String deleteMenus() {
        return menuService.deleteMenus();
    }

    /**
     * 新增菜单
     *
     * @return
     */
    @RequestMapping(value = "/addMenus", method = RequestMethod.POST)
    public String addMenus(String menuStr) {
        return menuService.addMenus(menuStr);
    }
}
