package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("report")
public class reportEntity {
    @TableId(value = "reportID",type = IdType.AUTO)
    private int reportID;
    @TableField("title")
    private String title;
    @TableField("state")
    private String state;

    @TableField("time")
    private String time;

    @TableField("url")
    private String url;

    @TableField("user_userID")
    private int user_userID;
}
