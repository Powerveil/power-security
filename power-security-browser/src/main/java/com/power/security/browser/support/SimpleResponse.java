package com.power.security.browser.support;

/**
 * @author Powerveil
 * @Date 2024/1/23 11:13
 */
public class SimpleResponse {

    public SimpleResponse(Object content) {
        this.content = content;
    }

    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
