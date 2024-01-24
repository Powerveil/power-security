package com.power.security.core.authentication;

import com.power.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author Powerveil
 * @Date 2024/1/24 16:48
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    protected AuthenticationSuccessHandler powerAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler powerAuthenticationFailureHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL) // 当请求需要身份认证时，默认跳转的url
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM) // 默认的用户名密码登录请求处理url
                .successHandler(powerAuthenticationSuccessHandler) // 登录成功处理器
                .failureHandler(powerAuthenticationFailureHandler); // 登录失败处理器
    }
}
