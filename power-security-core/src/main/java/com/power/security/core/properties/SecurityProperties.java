package com.power.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Powerveil
 * @Date 2024/1/23 11:19
 */
@Data
//@Component(value = "mySecurityProperties")
@ConfigurationProperties(prefix = "power.security")
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    private SocialProperties social = new SocialProperties();

}
