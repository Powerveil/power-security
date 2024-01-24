package com.power.security.core.properties;

import lombok.Data;

/**
 * @author Powerveil
 * @Date 2024/1/23 11:19
 */
@Data
public class BrowserProperties {
    private String loginPage = "/power_signIn.html";

    private LoginType loginType = LoginType.JSON;

    // 记住我Cookie的时间
    private int rememberMeSeconds = 3600;

}
