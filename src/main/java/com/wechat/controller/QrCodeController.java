package com.wechat.controller;

import com.wechat.service.qrcode.IQrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by rongyaowen
 * on 2019/1/16.
 * 二维码
 */
@Controller
@RequestMapping("/qrCode")
public class QrCodeController {
    @Autowired
    private IQrCodeService qrCodeService;

    /**
     * 获取临时二维码
     *
     * @return
     */
    @RequestMapping("/getTempQrCode")
    public String getTempQrCode() {
        return "redirect:" + qrCodeService.getTempQrcode();
    }

    /**
     * 获取临时二维码
     *
     * @return
     */
    @RequestMapping("/getPermanentQrCode")
    public String getPermanentQrCode() {
        return "redirect:" + qrCodeService.getPermanentQrCode();
    }

}
