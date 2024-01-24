package com.power.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Powerveil
 * @Date 2024/1/23 21:00
 */
public interface ValidateCodeGenerator {
    ValidateCode generate(ServletWebRequest request);
}
