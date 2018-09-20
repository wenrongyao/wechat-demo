package com.wechat.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by rongyaowen on 2018/9/10.
 */
@SpringBootApplication
@ComponentScan("com.wechat.*")
public class WeChatApplication {
    public static void main(String args[]){
        SpringApplication.run(WeChatApplication.class);
    }
}
