package com.power.security.core.properties;

import lombok.Data;

/**
 * @author Powerveil
 * @Date 2024/1/23 20:28
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties {
    private int width = 67;
    private int height = 23;

    public ImageCodeProperties() {
        setLength(4);
    }
}
