package com.power.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Powerveil
 * @Date 2024/1/23 17:33
 */
public class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = 2684568756875687568L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
