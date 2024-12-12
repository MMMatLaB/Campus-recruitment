package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("stu_want_career")
public class StuWantEntity {
    @TableId(value = "wantID",type = IdType.AUTO)
    private int wantID;

    @TableField("user_userID")
    private int useruserID;

    @TableField("career_careerID1")
    private int careercareerID1;

    public StuWantEntity(int uid,int cid){
        this.useruserID =uid;
        this.careercareerID1 =cid;
    }
    public StuWantEntity(){

    }
}
