package com.example.demo.domain;

import lombok.Data;

import java.io.Serializable;



@Data
public class Zhao_UserDO implements Serializable {

    //用户ID
    private Long userId;
    // 用户名
    private String username;
    // 用户真实姓名
    private String name;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
