package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("notice")
public class noticeEntity {
    @TableId(value = "noticeID",type = IdType.AUTO)
    private int noticeID;

    @TableField("title")
    private String title;

    @TableField("date")
    private String date;

    @TableField("type")
    private String type;

    @TableField("url")
    private String url;

    @TableField("user_userID")
    private int user_userID;


}
