package com.power.security.core.social.qq.api;

import java.io.IOException;

/**
 * @author Powerveil
 * @Date 2024/1/25 10:19
 */
public interface QQ {

    QQUserInfo getUserInfo() throws IOException;
}
