package com.power.security.browser;

import com.power.security.browser.authentication.PowerAuthenticationFailureHandler;
import com.power.security.browser.authentication.PowerAuthenticationSuccessHandler;
import com.power.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @author Powerveil
 * @Date 2024/1/22 16:31
 */
@Configurable
@EnableWebSecurity
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    //    @Resource(name = "mySecurityProperties")
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private PowerAuthenticationSuccessHandler powerAuthenticationSuccessHandler;

    @Autowired
    private PowerAuthenticationFailureHandler powerAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.cors().disable();
        http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(powerAuthenticationSuccessHandler) // 登录成功处理器
                .failureHandler(powerAuthenticationFailureHandler) // 登录失败处理器
//        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage()).permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
//
//        http.httpBasic().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}