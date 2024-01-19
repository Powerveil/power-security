package com.power.controller;

import com.power.dto.QueryUserDto;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Powerveil
 * @Date 2024/1/19 17:09
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/allUser")
//    @RequestMapping(value = "allUser", method = RequestMethod.GET)
    public List<String> allUser(@RequestParam(name = "query1", required = false) String query) {
        List<String> list = new ArrayList<>();
        list.add("test01");
        list.add("test02");
        list.add("test03");
        System.out.println(query);

        return list;
    }

    @GetMapping("/query")
//    @RequestMapping(value = "allUser", method = RequestMethod.GET)
    public List<String> query(QueryUserDto queryUserDto,@PageableDefault(page = 2, size = 17, sort = "username,asc") Pageable pageable) {
        List<String> list = new ArrayList<>();
        list.add("test01");
        list.add("test02");
        list.add("test03");
        System.out.println(queryUserDto);

        System.out.println(ReflectionToStringBuilder.toString(queryUserDto, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(ReflectionToStringBuilder.toString(pageable, ToStringStyle.MULTI_LINE_STYLE));

        return list;
    }
}
