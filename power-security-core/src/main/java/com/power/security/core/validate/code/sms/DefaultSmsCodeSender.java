package com.power.security.core.validate.code.sms;

/**
 * @author Powerveil
 * @Date 2024/1/24 11:23
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机" + mobile + "发送短信验证码" + code);
    }
}
