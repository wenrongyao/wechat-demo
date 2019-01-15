package com.wechat.constant;

/**
 * Created by rongyaowen
 * on 2019/1/15.
 * 常量类
 */
public class Constant {

    /**
     * 微信公众号参数
     */
    public class WechatAccount {
        public static final String APPID = "wx081e08a105222e83";
        public static final String APPSECRET = "3cbf1607266c940c22397c54a8069a94";
        public static final String TOKEN = "3TogaRqRElr";
        public static final String ENCODINGAESKEY = "eJ8JxunSiFjpDq3y05LuSO5A0Vt9Qlk1UUVGlkidCSP";
    }

    /**
     * 消息类型
     */
    public class MsgType {
        public static final String TEXT = "text";
        public static final String EVENT = "event";
    }

    /**
     * 事件类型
     */
    public class Event {
        // 订阅
        public static final String SUBSCRIBE = "subscribe";
    }
}
