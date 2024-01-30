package com.power.security.core.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @ClassName SocialConfig
 * @Description Social配置类
 * @Author Powerveil
 * @Date 2024/1/29 20:20
 * @Version 1.0
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {

        // 第一个参数 必须是spring管理的
        // 第二个参数 查找ConnectionFactory
        //   - 系统可能有很多个ConnectionFactory qq的或者微信的或者其他的 这个是根据条件来用哪一个ConnectionFactory
        //   - 构建Connection数据
        // 第三个参数 加密的密钥
        JdbcUsersConnectionRepository jdbcUsersConnectionRepository =
                new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        // 如果有规范表明要加前缀，设置表前缀
        jdbcUsersConnectionRepository.setTablePrefix("power_");
        return jdbcUsersConnectionRepository;
    }

    @Bean
    public SpringSocialConfigurer powerSocialSecurityConfig() {
        return new SpringSocialConfigurer();
    }
}
