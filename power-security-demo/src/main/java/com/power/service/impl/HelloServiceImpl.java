package com.power.service.impl;

import com.power.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author Powerveil
 * @Date 2024/1/19 23:05
 */
@Service
public class HelloServiceImpl implements HelloService {


    @Override
    public String greeting(String name) {
        System.out.println("HelloServiceImpl:greeting");
        return "hello " + name;
    }
}
