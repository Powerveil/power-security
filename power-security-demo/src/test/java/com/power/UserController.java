package com.power;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Powerveil
 * @Date 2024/1/19 16:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserController {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenQuerySuccess1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/allUser")
                        .param("query1", "你好")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 返回类型是集合，集合长度是3
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }

    @Test
    public void whenQuerySuccess2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/query")
                        .param("username", "张三")
                        .param("phone", "17630825698")
                        .param("address", "河南")
                        .param("size", "15")
                        .param("page", "3")
                        .param("sort", "datetime,desc")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 返回类型是集合，集合长度是3
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }

}