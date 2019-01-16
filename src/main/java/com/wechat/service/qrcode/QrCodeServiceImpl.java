package com.wechat.service.qrcode;

import com.wechat.constant.Constant;
import com.wechat.model.qrcode.QrCodeParam;
import com.wechat.util.GsonUtil;
import com.wechat.util.HttpRequest;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by rongyaowen
 * on 2019/1/16.
 */
@Service
public class QrCodeServiceImpl implements IQrCodeService {
    // 获取ticket
    private static final String GET_QRCODE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token={TOKEN}";
    // 换取二维码
    private static final String QR_CODE_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket={TICKET}";

    @Override
    public String getTempQrcode() {
        QrCodeParam qrCodeParam = new QrCodeParam();
        QrCodeParam.ActionInfo actionInfo = qrCodeParam.new ActionInfo();
        QrCodeParam.ActionInfo.Scene scene = actionInfo.new Scene();
        //设置场景值
        scene.setSceneStr("temp_qrcode_test");
        actionInfo.setScene(scene);
        qrCodeParam.setActionInfo(actionInfo);
        qrCodeParam.setActionName("QR_STR_SCENE");
        qrCodeParam.setExpireSeconds(604800);

        String param = GsonUtil.toJson(qrCodeParam);
        String qrCodeUrl = QR_CODE_URL.replace("{TICKET}", getTicket(param));
        System.out.println(qrCodeUrl);
        return qrCodeUrl;
    }

    @Override
    public String getPermanentQrCode() {
        QrCodeParam qrCodeParam = new QrCodeParam();
        QrCodeParam.ActionInfo actionInfo = qrCodeParam.new ActionInfo();
        QrCodeParam.ActionInfo.Scene scene = actionInfo.new Scene();
        //设置场景值
        scene.setSceneStr("permanent_qrcode_test");
        actionInfo.setScene(scene);
        qrCodeParam.setActionInfo(actionInfo);
        qrCodeParam.setActionName("QR_LIMIT_STR_SCENE");

        String param = GsonUtil.toJson(qrCodeParam);
        String qrCodeUrl = QR_CODE_URL.replace("{TICKET}", getTicket(param));
        System.out.println(qrCodeUrl);
        return qrCodeUrl;
    }

    /**
     * 获取ticket
     *
     * @param param
     * @return
     */
    private String getTicket(String param) {
        String url = GET_QRCODE_URL.replace("{TOKEN}", Constant.ACCESS_TOKEN);
        String jsonStr = HttpRequest.post(url, param, null, Constant.ContentType.APPLICATION_JSON, false);
        Map<String, Object> map = GsonUtil.fromJson(jsonStr, Map.class);
        return map.get("ticket").toString();
    }
}
