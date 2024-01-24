package com.power.filter;



import javax.servlet.*;
import java.io.IOException;

/**
 * @author Powerveil
 * @Date 2024/1/21 11:01
 * 这种方式有一个问题：只能拿到http的请求和响应，这个请求真正是由哪个控制器的方法处理的在Filter里面是不知道的
 * Filter是javax，J2EE规范来定义的，在J2EE规范中实际上不了解和Spring相关的任何东西
 * 而UserController是SpringMVC自己定义的一套，所以在Filter中我们是不知道是哪个控制器哪个方法来处理请求的
 */
//@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("my filter start");
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long end = System.currentTimeMillis();
        System.out.println("time filter:" + (end - start) + "ms");
        System.out.println("my filter finish");
    }

    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }
}
