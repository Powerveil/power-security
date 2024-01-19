package com.power.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Powerveil
 * @Date 2024/1/19 17:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public interface UserSimpleView {};
    public interface UserDetailView extends UserSimpleView {};

    @JsonView(UserSimpleView.class)
    private String username;
    @JsonView(UserDetailView.class)
    private String password;
//    private String sex;
//    private String phone;
//    private String address;
}
