package com.zilweney.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

    //姓名
    private String name;

    //用户名
    private String username;

//    //手机号
//    private String phone;

    //密码
    private String password;

    //性别 0 女 1 男
    private String sex;

    private String avator;
}
