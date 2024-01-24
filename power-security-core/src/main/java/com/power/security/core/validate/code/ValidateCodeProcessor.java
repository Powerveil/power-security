package com.power.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Powerveil
 * @Date 2024/1/24 11:42
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建短信验证码
     *
     * @param request
     */
    void create(ServletWebRequest request) throws Exception;
}
