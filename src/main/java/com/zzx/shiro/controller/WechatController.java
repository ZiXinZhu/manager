package com.zzx.shiro.controller;


import com.zzx.shiro.enums.PassEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/pay")
@Slf4j
public class WechatController {

    private Map<String, String> map = new ConcurrentHashMap();
    @PostMapping("/wechatpay")
    public synchronized void wechat(int passID, String openId, String amount, String name) throws IOException, InterruptedException {
        if(passID==0||openId.equals(null)||amount.equals(null)){
            return;
        }

        if (passID == PassEnum.WECHAT.getCode()) {
            wechatAay(openId,amount,name);
            Thread.sleep(20);
        }
    }
    public String wechatAay( String openId, String amount, String name) throws InterruptedException {
        log.info("{}支付方式", PassEnum.WECHAT.getPayType());
        String sign="http://pos.yeahka.com/pay/sytPay.html?random=2068835222&merchantId=5602109441&t0=0&amount="+amount+"&source_type=33&qrcodeNum=SYT1800003860&source=weixinpay&openId="+openId+"&merchantName="+name+"&isOpenFastPay=0&urlId=123465&uniqueId=o8uJ6uNop4ZpFMdrhMJwR8FgyXYc";
        return sign;
    }
}
