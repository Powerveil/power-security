package com.power.security.core.validate.code.sms;

/**
 * @author Powerveil
 * @Date 2024/1/24 11:22
 */
public interface SmsCodeSender {
    void send(String mobile, String code);
}
