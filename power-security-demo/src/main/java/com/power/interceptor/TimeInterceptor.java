package com.power.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Powerveil
 * @Date 2024/1/21 11:24
 */
@Component
// 拦截器会拦截所有控制器的controller方法，spring提供的controller方法也会被拦截
// 可以拿到请求、响应以及控制器真正处理的请求的方法对象
// 但是他也有一些问题，没有办法拿到方法真正参数的值

public class TimeInterceptor implements HandlerInterceptor {
    // 执行方法前调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");

        System.out.println(handler);

        System.out.println(((HandlerMethod) handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod) handler).getMethod());

        request.setAttribute("startTime", new Date().getTime());
        return true;
    }

    // 执行方法后调用
    // 如果抛出异常不会调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    // 不管控制器方法是正常完成还是抛出异常都会调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {


        long startTime = (long) request.getAttribute("startTime");
        long endTime = new Date().getTime();
        long time = endTime - startTime;
        System.out.println("time interceptor:" + time + "ms");


        System.out.println("exception:" + e);
        System.out.println("afterCompletion");
    }
}
