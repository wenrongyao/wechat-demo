package com.wechat.controller;

import com.wechat.constant.Constant;
import com.wechat.util.aes.AesException;
import com.wechat.util.aes.WXBizMsgCrypt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by rongyaowen on 2018/9/10.
 */
@RestController
public class BaseController {

    @RequestMapping(value = "/entrance")
    public String entryTest(HttpServletRequest request) throws Exception {
        // 微信加密签名
        String msgSignature = request.getParameter("signature");
        // 时间戳
        String timeStamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echoStr = request.getParameter("echostr");

        String result = null;
        try {
            // 创建加密类
            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(Constant.WechatAccount.TOKEN, Constant.WechatAccount.ENCODINGAESKEY, Constant.WechatAccount.APPID);
            // 比对msgSignature 用token, timeStamp, nonce加密的参数是否一致，一致证明该接口来自微信，异常则不是来自微信
            result = wxcpt.verifyUrl_WXGZ(msgSignature, Constant.WechatAccount.TOKEN, timeStamp, nonce, echoStr);
        } catch (AesException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }


}
