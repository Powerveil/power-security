package com.power.security.core.properties;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author Powerveil
 * @Date 2024/1/23 11:19
 */
public class BrowserProperties {
    private String loginPage = "/power_signIn.html";

    private LoginType loginType = LoginType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
