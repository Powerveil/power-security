package com.power.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.power.dto.QueryUserDto;
import com.power.pojo.User;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Powerveil
 * @Date 2024/1/19 17:09
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/allUser0")
//    @RequestMapping(value = "allUser", method = RequestMethod.GET)
    public List<String> allUser0(@RequestParam(name = "query1", required = false) String query) {
        List<String> list = new ArrayList<>();
        list.add("test01");
        list.add("test02");
        list.add("test03");
        System.out.println(query);

        return list;
    }


    @GetMapping("/allUser1")
    @JsonView(User.UserSimpleView.class)
    public List<User> allUser1(@RequestParam(name = "query1", required = false) String query) {
        List<User> list = new ArrayList<>();
        list.add(new User("zhangsan", "123456"));
        list.add(new User("哭诉", "123456"));
        list.add(new User("ss", "123456"));
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

//    // 可以使用正则表达式
//    @GetMapping("/{userId:\\d+}")
////    @RequestMapping(value = "allUser", method = RequestMethod.GET)
//    public HashMap<String, String> getUserById0(@PathVariable(value = "userId") String userId) {
//        System.out.println("userId = " + userId);
//        HashMap<String, String> map = new HashMap<>();
//        map.put("username", "张三");
//        return map;
//    }



    // 可以使用正则表达式
    @GetMapping("/{userId:\\d+}")
    @JsonView(User.UserDetailView.class)
//    @RequestMapping(value = "allUser", method = RequestMethod.GET)
    public User getUserById1(@PathVariable(value = "userId") String userId) {
        System.out.println("userId = " + userId);
        HashMap<String, String> map = new HashMap<>();
        return new User("张三", "123456");
    }
}
