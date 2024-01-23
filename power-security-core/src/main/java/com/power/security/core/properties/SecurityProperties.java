package com.power.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Powerveil
 * @Date 2024/1/23 11:19
 */
//@Component(value = "mySecurityProperties")
@ConfigurationProperties(prefix = "power.security")
public class SecurityProperties {
    BrowserProperties browser = new BrowserProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
