package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("questionnaire")
public class QuestionnaireEntity {
    @TableId(value = "idquestionnaire",type = IdType.AUTO)
    private int idquestionnaire;
    private String fileName;
    private String intro;
    private String title;
    private int state;
    private String creattime;
    private int user_userID;


}
