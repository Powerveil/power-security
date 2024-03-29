package com.power.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Powerveil
 * @Date 2024/1/21 10:51
 */
@ControllerAdvice
// 异常处理器是在拦截器之前的，如果异常处理器处理掉了，拦截器的afterCompletion的入参exception为null
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> ControllerExceptionHandler(UserNotFoundException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", e.getId());
        result.put("message", e.getMessage());
        return result;
    }
}
