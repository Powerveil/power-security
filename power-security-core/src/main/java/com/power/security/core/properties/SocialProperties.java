package com.power.security.core.properties;

import lombok.Data;

/**
 * @ClassName SocailProperties
 * @Description Social全局配置类
 * @Author Powerveil
 * @Date 2024/1/29 20:43
 * @Version 1.0
 */
@Data
public class SocialProperties {
    private QQProperties qq = new QQProperties();

}
