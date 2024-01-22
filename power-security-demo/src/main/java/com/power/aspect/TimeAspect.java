package com.power.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Powerveil
 * @Date 2024/1/21 16:55
 */
//@Aspect
//@Component
// 可以拿到请求方法和请求参数，但是拿不到http的请求和响应
public class TimeAspect {

    @Around("execution(* com.power.controller.UserController.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("time aspect start");
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("arg is " + arg);
        }

        long start = new Date().getTime();
        Object result = joinPoint.proceed();
        long end = new Date().getTime();
        System.out.println("time aspect 耗时" + (end - start) + "ms");
        System.out.println("time aspect end");
        return result;
    }

}
