package com.wechat.service.msg;

import com.wechat.constant.Constant;
import com.wechat.model.msg.TextReplyMsg;
import com.wechat.util.GsonUtil;
import com.wechat.util.HttpRequest;
import com.wechat.util.XmlUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by rongyaowen
 * on 2019/1/15.
 * 回复消息
 */
@Service
public class MsgServiceImpl implements IMsgService {
    // 机器人接口
    private static final String AITEXTREPLYURL = "http://api.qingyunke.com/api.php?key=free&appid=0&msg=";

    @Override
    public String returnText(Map<String, String> map) {
        // 构建回复消息
        TextReplyMsg textReplyMsg = new TextReplyMsg();
        textReplyMsg.setToUserName(map.get("FromUserName"));
        textReplyMsg.setFromUserName(map.get("ToUserName"));
        textReplyMsg.setCreateTime(new Date().getTime());
        textReplyMsg.setMsgType(Constant.MsgType.TEXT);

        String url = AITEXTREPLYURL + map.get("Content");
        // {"result":0,"content":"*^_^*好好好~"}
        String aiMessageStr = HttpRequest.get(url, null, false);
        Map<String, Object> aiMap = GsonUtil.fromJson(aiMessageStr, Map.class);
        textReplyMsg.setContent(aiMap.get("content").toString());
        // 将pojo对象转成xml
        XmlUtil.xstream.alias("xml", TextReplyMsg.class);
        return XmlUtil.xstream.toXML(textReplyMsg);
    }

    @Override
    public String returnText(Map<String, String> map, String content) {
        // 构建回复消息
        TextReplyMsg textReplyMsg = new TextReplyMsg();
        textReplyMsg.setToUserName(map.get("FromUserName"));
        textReplyMsg.setFromUserName(map.get("ToUserName"));
        textReplyMsg.setCreateTime(new Date().getTime());
        textReplyMsg.setMsgType(Constant.MsgType.TEXT);
        textReplyMsg.setContent(content);
        // 将pojo对象转成xml
        XmlUtil.xstream.alias("xml", TextReplyMsg.class);
        return XmlUtil.xstream.toXML(textReplyMsg);
    }
}
