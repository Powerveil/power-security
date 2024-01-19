package com.power.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import com.power.validator.MyConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

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
    private String id;
    @JsonView(UserSimpleView.class)
    @MyConstraint(message = "用户名不能为空【这是一个测试】")
    private String username;
    @JsonView(UserDetailView.class)
    @NotBlank(message = "密码不能为空")
    private String password;
    @JsonView(UserSimpleView.class)
    @Past(message = "生日不能大于当前时间")
    private Date birthday;
//    private String sex;
//    private String phone;
//    private String address;
}
