package com.power.controller;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;
import com.power.dto.QueryUserDto;
import com.power.exception.UserNotFoundException;
import com.power.pojo.User;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

        User user1 = new User();
        user1.setId("1");
        user1.setUsername("张三");
        user1.setPassword("123456");


        User user2 = new User();
        user2.setId("1");
        user2.setUsername("张三");
        user2.setPassword("123456");


        User user3 = new User();
        user3.setId("1");
        user3.setUsername("张三");
        user3.setPassword("123456");
        
        
        list.add(user1);
        list.add(user2);
        list.add(user3);
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

//        throw new RuntimeException("user not found");
//        throw new UserNotFoundException(userId);
        System.out.println("进入getUserById1");

        System.out.println("userId = " + userId);
        User user = new User();
        user.setId("1");
        user.setUsername("张三");
        user.setPassword("123456");

        return user;
    }

    @PostMapping
    @JsonView(User.UserSimpleView.class)
    public User create(@Valid @RequestBody User user) {
        System.out.println(user);
        user.setId("1");
        return user;
    }


    @PutMapping("/{userId}")
    @JsonView(User.UserSimpleView.class)
    public User update(@PathVariable(value = "userId") String userId, @Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            FieldError fieldError = errors.getFieldError();
            String field = fieldError.getField();
            System.out.println(field);
            errors.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(user);
        user.setId(userId);
        return user;
    }

    @DeleteMapping(value = "/{userId}")
    public void delete(@PathVariable(value = "userId") String userId) {
        System.out.println("userId = " + userId);
    }
}
