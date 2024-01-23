package com.power.security.core.properties;

import lombok.Data;

/**
 * @author Powerveil
 * @Date 2024/1/23 20:28
 */
@Data
public class ImageCodeProperties {
    private int width = 67;
    private int height = 23;

    private int length = 4;
    private int expireIn = 60;

    private String url;

}
