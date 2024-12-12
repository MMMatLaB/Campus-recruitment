package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
    private int  userid;
    private String username;
    private String password;
    private String email;
    private String telephone;



    public User() {
    }

    public User(int id, String username, String password, String email, String phone) {
        this.userid = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telephone = phone;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + telephone + '\'' +
                '}';
    }

}


