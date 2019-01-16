package com.wechat.controller;

import com.google.gson.annotations.SerializedName;
import com.wechat.constant.Constant;
import com.wechat.model.user.WeChatUserInfo;
import com.wechat.util.GsonUtil;
import com.wechat.util.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by rongyaowen on 2018/9/10.
 */
@RestController
@RequestMapping("/authorization")
public class AuthorizationController {
    private static final String GETACCESSTOKENURL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={appId}&secret={secret}&code={code}&grant_type=authorization_code";
    private static final String GETUSERINFOURL = "https://api.weixin.qq.com/sns/userinfo?access_token={accessToken}&openid={openId}&lang=zh_CN";

    /**
     * 静默授权
     *
     * @return
     */
    @RequestMapping("/snsApiBase")
    public String authorizationBase(String code) {
        ReturnMessage returnMessage = getAccesstoken(code);
        return "静默授权：oppenId=" + returnMessage.getOpenId();
    }

    /**
     * 非静默授权
     *
     * @return
     */
    @RequestMapping("/snsApiUserInfo")
    public String authorizationUseInfo(String code) {
        ReturnMessage returnMessage = getAccesstoken(code);
        String getUserInfoUrl = GETUSERINFOURL.replace("{accessToken}", returnMessage.getAccessToken()).replace("{openId}", returnMessage.getOpenId());
        String userInfoStr = HttpRequest.get(getUserInfoUrl, null, false);
        WeChatUserInfo weChatUserInfo = GsonUtil.fromJson(userInfoStr, WeChatUserInfo.class);
        return "非静默授权：" + weChatUserInfo.toString();
    }

    /**
     * 获取accessToken,和统一管理的accessToken是不同的
     *
     * @param code
     * @return
     */
    private ReturnMessage getAccesstoken(String code) {
        String getAccessTokenurl = GETACCESSTOKENURL.replace("{appId}", Constant.WechatAccount.APPID).replace("{secret}", Constant.WechatAccount.APPSECRET).replace("{code}", code);
        String returnMessageStr = HttpRequest.get(getAccessTokenurl, null, false);
        return GsonUtil.fromJson(returnMessageStr, ReturnMessage.class);
    }
}

/**
 * 返回结果
 */
class ReturnMessage {
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("expires_in")
    private String expiresIn;
    @SerializedName("refresh_token")
    private String refreshToken;
    @SerializedName("openid")
    private String openId;
    @SerializedName("scope")
    private String scope;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
