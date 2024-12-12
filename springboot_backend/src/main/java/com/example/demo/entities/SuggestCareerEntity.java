package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("suggest_career")
public class SuggestCareerEntity {
    @TableId(value = "suggestID",type = IdType.AUTO)
    private int suggestID;

    @TableField("ratio")
    private float ratio;

    @TableField("user_userID")
    private int user_userID;;

    @TableField("career_careerID1")
    private int career_careerID1;

    public void resetSC(float ratio,int user_userID,int career_careerID1){
        this.ratio=ratio;
        this.user_userID=user_userID;
        this.career_careerID1=career_careerID1;

    }
}
