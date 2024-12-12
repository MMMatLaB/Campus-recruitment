package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("career")
public class careerEntity {
    @TableId(value = "careerID",type = IdType.AUTO)
    private int careerID;
    @TableField("careerName")
    private String careerName;
    @TableField("salarymax")
    private String salarymax;
    @TableField("educationlevel")
    private String educationlevel;
    @TableField("workyear")
    private String workyear;
    @TableField("careerInfo")
    private String careerInfo;
    @TableField("workplace")
    private String workplace;
    @TableField("creatdtime")
    private String creatdtime;
    @TableField("salarymin")
    private String salarymin;
    @TableField("status")
    private int status;
    @TableField("corporationidcorporation")
    private int corporationidcorporation;
}
