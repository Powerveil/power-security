package com.power.security.core.validate.code.sms;

import com.power.security.core.properties.SecurityProperties;
import com.power.security.core.validate.code.ValidateCode;
import com.power.security.core.validate.code.ValidateCodeGenerator;
import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author Powerveil
 * @Date 2024/1/23 21:01
 */
@Data
@Component("smsCodeGenerator")// 这里直接注入spring容器，因为它没有向图形验证码那样有各种各样的生成逻辑
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }

}
