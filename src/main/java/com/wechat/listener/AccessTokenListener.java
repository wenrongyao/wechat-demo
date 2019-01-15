package com.wechat.listener;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by rongyaowen
 * on 2019/1/15.
 */
@WebListener
public class AccessTokenListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("监听器初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
