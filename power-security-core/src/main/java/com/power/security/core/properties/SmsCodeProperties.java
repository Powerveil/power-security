package com.power.security.core.properties;

import lombok.Data;

/**
 * @author Powerveil
 * @Date 2024/1/23 20:28
 */
@Data
public class SmsCodeProperties {
    private int length = 6;
    private int expireIn = 60;

    private String url;

}
