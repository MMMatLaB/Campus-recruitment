package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("questionnaire")
public class questionEntity {
    @TableId(value = "idquestionnaire",type = IdType.AUTO)
    private int idquestionnaire;

    @TableField("fileName")
    private String path;

    @TableField("intro")
    private String Abstract;

    @TableField("title")
    private String title;

    @TableField("state")
    private String state;

    @TableField("user_userID")
    private int user_userID;

    @TableField("creattime")
    private String creattime;

    //没放json
}
