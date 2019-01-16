package com.wechat.service.menu;

/**
 * Created by rongyaowen
 * on 2019/1/16.
 */
public interface IMenuService {

    /**
     * 获取所有菜单
     *
     * @return
     */
    public String getMenus();

    /**
     * 删除所有菜单
     *
     * @return
     */
    public String deleteMenus();

    /**
     * 新增菜单
     *
     * @param menuStr
     * @return
     */
    String addMenus(String menuStr);
}
