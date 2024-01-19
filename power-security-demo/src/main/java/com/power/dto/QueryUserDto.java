package com.power.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Powerveil
 * @Date 2024/1/19 17:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryUserDto {
    private String username;
    private String phone;
    private String address;
}
