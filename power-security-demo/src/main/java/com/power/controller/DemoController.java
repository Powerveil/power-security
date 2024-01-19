package com.power.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Powerveil
 * @Date 2024/1/18 16:03
 */
@RestController
public class DemoController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello spring security";
    }
}
