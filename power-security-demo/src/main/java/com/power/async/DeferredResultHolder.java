package com.power.async;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Powerveil
 * @Date 2024/1/22 11:05
 */
@Data
@Component
public class DeferredResultHolder {
    // key为订单号，value是处理结果

    private Map<String, DeferredResult<String>> map = new ConcurrentHashMap<>();
}
