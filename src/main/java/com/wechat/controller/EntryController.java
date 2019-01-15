package com.wechat.controller;

import com.wechat.constant.Constant;
import com.wechat.model.msg.Message;
import com.wechat.service.msg.IMsgService;
import com.wechat.util.XmlUtil;
import com.wechat.util.aes.AesException;
import com.wechat.util.aes.WXBizMsgCrypt;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rongyaowen on 2018/9/10.
 */
@RestController
public class EntryController {

    @Autowired
    private IMsgService msgService;

    @Autowired
    private Message message;

    @RequestMapping(value = "/entrance", produces = "application/json;charset=utf-8")
    public String entryTest(HttpServletRequest request) throws Exception {
        // 微信加密签名
        String msgSignature = request.getParameter("signature");
        // 时间戳
        String timeStamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echoStr = request.getParameter("echostr");
        Map<String, String> map = null;
        String msgType = null;

        try {
            // 解析xml数据，将解析结果存储在HashMap中
            map = new HashMap<>();
            // 读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(request.getInputStream());
            // 得到xml根元素
            Element root = document.getRootElement();
            XmlUtil.parserXml(root, map);
            if (null != map && !map.isEmpty()) {
                // 消息类型
                msgType = map.get("MsgType");
            }
        } catch (DocumentException e) {
        } catch (IOException e) {
        }

        String result = null;
        try {
            if (StringUtils.isEmpty(msgType)) {
                // 创建加密类
                WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(Constant.WechatAccount.TOKEN, Constant.WechatAccount.ENCODINGAESKEY, Constant.WechatAccount.APPID);
                // 比对msgSignature 用token, timeStamp, nonce加密的参数是否一致，一致证明该接口来自微信，异常则不是来自微信
                result = wxcpt.verifyUrl_WXGZ(msgSignature, Constant.WechatAccount.TOKEN, timeStamp, nonce, echoStr);
            } else {
                if (msgType.equals(Constant.MsgType.TEXT)) {
                    result = msgService.returnText(map);
                } else if (msgType.equals(Constant.MsgType.EVENT)) {
                    String event = map.get("Event");
                    if (event.equals(Constant.Event.SUBSCRIBE)) { // 关注公众号
                        result = msgService.returnText(map, message.getSubscribe());
                    }
                }
            }
        } catch (AesException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }
}
