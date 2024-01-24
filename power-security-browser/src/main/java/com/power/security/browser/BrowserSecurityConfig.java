package com.power.security.browser;

import com.power.security.browser.authentication.PowerAuthenticationFailureHandler;
import com.power.security.browser.authentication.PowerAuthenticationSuccessHandler;
import com.power.security.core.authentication.AbstractChannelSecurityConfig;
import com.power.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.power.security.core.properties.SecurityConstants;
import com.power.security.core.properties.SecurityProperties;
import com.power.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


/**
 * @author Powerveil
 * @Date 2024/1/22 16:31
 */
@Configurable
@EnableWebSecurity
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 启动的时候创建表
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);
        // 验证码（图形验证码或短信验证码）校验配置
        http.apply(validateCodeSecurityConfig)
                .and()
                // 验证码认证过滤器
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .rememberMe()
                // 配置tokenRepository
                .tokenRepository(persistentTokenRepository())
                // 记住我时间
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                // 配置用户信息服务
                .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }
}
