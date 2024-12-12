package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("report")
public class Report {
    @TableId(value = "reportID",type = IdType.AUTO)
    private int reportID;
    private String title;
    private int state;
    private String time;
    private String url;
    private int user_userID;
}
