package com.power.config;

import com.power.filter.TimeFilter;
import com.power.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Powerveil
 * @Date 2024/1/21 11:12
 */
//@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeInterceptor timeInterceptor;

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        // 如果需要拦截异步请求，需要使用这两种方法
//        configurer.registerCallableInterceptors();
//        configurer.registerDeferredResultInterceptors();
        // 开启异步请求超时
//        configurer.setDefaultTimeout();
        // 可以自己设置可重用的线程池来替代spring默认不重用的线程池
//        configurer.setTaskExecutor();
        super.configureAsyncSupport(configurer);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }


    // 把第三方Filter加到SpringBoot的Filter中
//    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new TimeFilter());

        List<String> urls = new ArrayList<>();
        urls.add("/*");
        registration.setUrlPatterns(urls);

        return registration;
    }
}
