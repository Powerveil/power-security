package com.power.security.core.social.qq.config;

import com.power.security.core.properties.QQProperties;
import com.power.security.core.properties.SecurityProperties;
import com.power.security.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName QQAutoConfig
 * @Description 将配置注入ConnectionFactory
 * @Author Powerveil
 * @Date 2024/1/29 20:46
 * @Version 1.0
 */
@Configuration
// 当配置文件里面power.security.social.qq app-id被配置了值下面才会生效
@ConditionalOnProperty(prefix = "power.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qq = securityProperties.getSocial().getQq();
        String providerId = qq.getProviderId();
        String appId = qq.getAppId();
        String appSecret = qq.getAppSecret();
        return new QQConnectionFactory(providerId, appId, appSecret);
    }
}
