package com.wechat.service.qrcode;

/**
 * Created by rongyaowen
 * on 2019/1/16.
 * 二维码service
 */
public interface IQrCodeService {
    /**
     * 获取临时二维码
     *
     * @return
     */
    public String getTempQrcode();

    /**
     * 获取永久二维码
     *
     * @return
     */
    public String getPermanentQrCode();
}
