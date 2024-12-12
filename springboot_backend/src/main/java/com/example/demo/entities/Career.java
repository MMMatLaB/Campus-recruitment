package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("career")
public class Career {
//    @TableId(value = "careerID",type = IdType.AUTO)
//    private int careerID;
//    @TableField("educationlevel")
//    private String educationRequirement;
//    @TableField("workyear")
//    private String workExperience;
//    @TableField("workplace")
//    private String jobLocation;
//    @TableField("salarymin")
//    private int salarymin;
//    @TableField("salarymax")
//    private int salarymax;
//    @TableField("status")
//    private int status;
//    @TableField("careerInfo")
//    private String info;
//    @TableField("creatdtime")
//    private String time;
//    @TableField("careerName")
//    private String name;
//    @TableField("corporationidcorporation")
//    private int corporationidcorporation;
    @TableId(value = "careerID",type = IdType.AUTO)
    private int careerID;
    @TableField("educationlevel")
    private String educationlevel;
    @TableField("workyear")
    private String workyear;
    @TableField("workplace")
    private String workplace;
    @TableField("salarymin")
    private int salarymin;
    @TableField("salarymax")
    private int salarymax;
    @TableField("status")
    private int status;
    @TableField("careerInfo")
    private String careerInfo;
    @TableField("creatdtime")
    private String creatdtime;
    @TableField("careerName")
    private String careerName;
    @TableField("corporationidcorporation")
    private int corporationidcorporation;
}
