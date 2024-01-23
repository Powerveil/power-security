package com.power.security.core;

import com.power.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Powerveil
 * @Date 2024/1/23 11:23
 */
@Configuration
@EnableConfigurationProperties({SecurityProperties.class})
public class SecurityCoreConfig {
}
