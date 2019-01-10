package com.zzx.shiro.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zzx.shiro.enums.PassEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/pay")
@Slf4j
public class AlipayController {

    private Map<String, String> map = new ConcurrentHashMap();
    /**
     * 阿里支付核心
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @PostMapping("/alipay")
    public synchronized void enter(int passID, String usrID, String money, String other, String name, String orderID, String AgentID, String type, HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        if (passID == 0 || usrID.equals(null) || money.equals(null) || other.equals(null) || name.equals(null)) {
            return;
        }
        if (passID == PassEnum.ALIPAY.getCode()) {
            alipay(usrID, money, other, name, orderID, AgentID, type, request, response);
            response.sendRedirect("../alipay.html");
            Thread.sleep(20);
        }
    }

    /**
     * Alipay支付方式
     *
     * @throws IOException
     */
    public void alipay(String usrID, String money, String other, String name, String orderID, String AgentID, String type, HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        String bgmoney;
        int imoney = Integer.parseInt(money);
        int first = imoney / 100;
        int last = imoney % 100;
        if (last / 10 == 0) {
            bgmoney = first + ".0" + last;
        } else {
            bgmoney = first + "." + last;
        }
        String sign = "alipays://platformapi/startapp?appId=20000067&amp;url=http%3A%2F%2F120.76.58.18%2Fpay%2Findex%2Falipay2%3Fms%3D" + other + "%26money%3D" + bgmoney + "%26uid%3D" + usrID + "";
        String lastName = name;
        String signtwo = "http://120.76.58.18/pay/index/alipay?ms=" + other + "&money=" + bgmoney + "&uid=" + usrID + "";
        log.info("{}支付方式", PassEnum.ALIPAY.getPayType());
        while (map.get("sign")!=null){
            Thread.sleep(1000);
        }
        map.put("sign", sign);
        map.put("money", bgmoney);
        map.put("names", lastName);
        map.put("signtwo", signtwo);
    }

    /**
     * Alipay前端回调
     *
     * @return
     */
    @PostMapping("/reAlipay")
    public Object reAlipay() {
        Object o=JSONObject.parseObject(JSON.toJSONString(map));
        map.clear();
        return o;
    }




}
