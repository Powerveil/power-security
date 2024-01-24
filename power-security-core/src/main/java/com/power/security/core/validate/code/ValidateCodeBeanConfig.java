package com.power.security.core.validate.code;

import com.power.security.core.properties.SecurityProperties;
import com.power.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.power.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Powerveil
 * @Date 2024/1/23 21:05
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    // 不直接加配置注解的原因：如果在spring配置容器里找不到这个bean才会创建，如果找到就不创建
    // 这里体现一个常见的代码开发的思想：以增量的方式去适应变化（重要的开发技巧）
    // ---- 当我生成图形验证码的逻辑改变了，原来的不满足了，我处理的方式不是去改原来的代码
    // ---- 而是新加了一段代码，这是一个架构师或者高级程序员必须掌握的技巧
    // ---- 只有掌握了这个技巧才能保证在别人不改你写的代码的情况下，能改变提供模块的业务逻辑
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    @Bean
    // 不直接加配置注解的原因：如果在spring配置容器里找不到这个bean才会创建，如果找到就不创建
    // 这里体现一个常见的代码开发的思想：以增量的方式去适应变化（重要的开发技巧）
    // ---- 当我生成图形验证码的逻辑改变了，原来的不满足了，我处理的方式不是去改原来的代码
    // ---- 而是新加了一段代码，这是一个架构师或者高级程序员必须掌握的技巧
    // ---- 只有掌握了这个技巧才能保证在别人不改你写的代码的情况下，能改变提供模块的业务逻辑
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}
