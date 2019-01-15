package com.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by rongyaowen on 2018/9/10.
 */
@SpringBootApplication
@ServletComponentScan
public class WeChatApplication {
    public static void main(String args[]){
        SpringApplication.run(WeChatApplication.class);
    }
}
