package com.power.security.core.social.qq.connect;

import com.power.security.core.social.qq.api.QQ;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * @ClassName QQConnectionFactory
 * @Description ConnectionFactory for QQ OAuth2
 * @Author Powerveil
 * @Date 2024/1/29 20:11
 * @Version 1.0
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {
    /**
     * Create a {@link OAuth2ConnectionFactory}.
     *
     * @param providerId 提供商的唯一标识
     * @param openId     用户唯一标识
     * @param appSecret
     */
    public QQConnectionFactory(String providerId, String openId, String appSecret) {
        super(providerId, new QQServiceProvider(openId, appSecret), new QQAdapter());
    }
}
