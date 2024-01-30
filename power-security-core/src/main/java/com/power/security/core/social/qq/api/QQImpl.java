package com.power.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * @author Powerveil
 * @Date 2024/1/25 10:20
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";


    private String appId;

    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();


    public QQImpl(String accessToken, String appId) {
        // 将accessToken放到url里面去
        // Indicates that the access token should be carried as a query parameter named "access_token".
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;

        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        System.out.println(result);

        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    }


    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USERINFO, this.appId, this.openId);
        String result = getRestTemplate().getForObject(url, String.class);
        System.out.println(result);
        QQUserInfo qqUserInfo = null;
        try {
            qqUserInfo = objectMapper.readValue(result, QQUserInfo.class);
        } catch (IOException e) {
            throw new RuntimeException("获取用户信息失败");
        }
        return qqUserInfo;
    }
}
