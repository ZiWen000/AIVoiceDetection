package com.zilweney.entity;

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
public class User implements Serializable {

//    private static final long serialVersionUID = 1L;

    private Long id;

//    //微信用户唯一标识
//    private String openid;

    //姓名
    private String name;

    private String username;

    //手机号
    private String phone;

    //密码
    private String password;

    //性别 0 女 1 男
    private String sex;


    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
