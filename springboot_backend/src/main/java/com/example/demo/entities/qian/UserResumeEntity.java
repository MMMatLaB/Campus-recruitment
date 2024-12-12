package com.example.demo.entities.qian;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class UserResumeEntity {
    private String userID;
    private String realName;
    private String tel;
    private String workplace;
    private String educationlevel;
    private String age;
    private String workyear;
    private String idealcity;
    private String idealsalary;
    private String idealcareer;
    private String idealdomain;
    private String description;
}
