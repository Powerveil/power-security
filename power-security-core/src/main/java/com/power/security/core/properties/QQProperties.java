package com.power.security.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @ClassName QQProperties
 * @Description QQ配置类
 * @Author Powerveil
 * @Date 2024/1/29 20:42
 * @Version 1.0
 */
@Data
public class QQProperties extends SocialProperties {

    private String providerId = "qq";
}
