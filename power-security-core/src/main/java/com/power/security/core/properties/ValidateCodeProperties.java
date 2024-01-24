package com.power.security.core.properties;

import lombok.Data;

/**
 * @author Powerveil
 * @Date 2024/1/23 20:31
 */
@Data
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();
    private SmsCodeProperties sms = new SmsCodeProperties();
}
