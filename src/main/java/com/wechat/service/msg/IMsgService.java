package com.wechat.service.msg;

import java.util.Map;

/**
 * Created by rongyaowen
 * on 2019/1/15.
 * 回复消息
 */
public interface IMsgService {

    /**
     * 回复文本信息
     *
     * @param map
     * @return
     */
    public String returnText(Map<String, String> map);

    /**
     * 回复文本信息
     *
     * @param map
     * @param content 消息内容
     * @return
     */
    public String returnText(Map<String, String> map, String content);
}
