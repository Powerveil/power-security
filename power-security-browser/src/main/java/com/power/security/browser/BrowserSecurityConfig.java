package com.power.security.browser;

import com.power.security.browser.authentication.PowerAuthenticationFailureHandler;
import com.power.security.browser.authentication.PowerAuthenticationSuccessHandler;
import com.power.security.core.properties.SecurityProperties;
import com.power.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


/**
 * @author Powerveil
 * @Date 2024/1/22 16:31
 */
@Configurable
@EnableWebSecurity
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private PowerAuthenticationSuccessHandler powerAuthenticationSuccessHandler;

    @Autowired
    private PowerAuthenticationFailureHandler powerAuthenticationFailureHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

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

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(powerAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        // 调用初始化方法
        validateCodeFilter.afterPropertiesSet();
//        http.cors().disable();
        http
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(powerAuthenticationSuccessHandler) // 登录成功处理器
                .failureHandler(powerAuthenticationFailureHandler) // 登录失败处理器
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository()) // 配置tokenRepository
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds()) // 记住我时间
                .userDetailsService(userDetailsService) // 配置用户信息服务
//        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/*").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
//
//        http.httpBasic().disable();
    }
}
