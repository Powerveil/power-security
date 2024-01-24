package com.power.security.core.validate.code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Powerveil
 * @Date 2024/1/23 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateCode {
    public ValidateCode(String code, int expireTime) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }


    private String code;

    private LocalDateTime expireTime;


    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
