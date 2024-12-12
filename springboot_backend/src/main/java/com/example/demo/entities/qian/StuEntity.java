package com.example.demo.entities.qian;

import lombok.Data;

@Data
public class StuEntity {
    private String stuId;
    private String name;
    private String major;
    private String tel;
    private int status;

    private String passwd;
}
