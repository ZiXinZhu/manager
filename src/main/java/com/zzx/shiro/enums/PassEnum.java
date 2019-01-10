package com.zzx.shiro.enums;

public enum PassEnum {
    ALIPAY(1,"支付宝"),WECHAT(2,"微信");
    private int code;
    private String payType;

    private PassEnum(int code, String payType) {
        this.code = code;
        this.payType = payType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
