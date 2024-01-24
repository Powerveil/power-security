package com.power.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.power.security.core.properties.LoginType;
import com.power.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Powerveil
 * @Date 2024/1/23 14:33
 */
@Component("powerAuthenticationSuccessHandler")
// 之前缓存请求的那个成功处理器
public class PowerAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * @param request        the request which caused the successful authentication
     * @param response       the response
     * @param authentication 封装认证信息（认证请求的信息，如IP，session。以及认证成功之后UserDetailsService返回的UserDetails）
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功");

        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            // 父类方法就是跳转
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
