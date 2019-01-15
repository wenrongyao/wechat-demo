package com.wechat.model.msg;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by rongyaowen
 * on 2019/1/15.
 */
@Component
@ConfigurationProperties(locations = {"classpath:message.properties"}, prefix = "message")
public class Message {
    private String subscribe;

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }
}
