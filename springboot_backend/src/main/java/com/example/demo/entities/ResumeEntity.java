package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.entities.qian.UserResumeEntity;
import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@TableName("Resume")
public class ResumeEntity {
    @TableId(value = "idResume",type = IdType.AUTO)
    private int idResume;
    @TableField("workplace")
    private String workplace;
    @TableField("educationlevel")
    private String educationlevel;
    @TableField("age")
    private String age;
    @TableField("workyear")
    private String workyear;
    @TableField("idealcity")
    private String idealcity;
    @TableField("idealsalary")
    private String idealsalary;
    @TableField("idealcareer")
    private String idealcareer;
    @TableField("idealdomain")
    private String idealdomain;
    @TableField("score")
    private int score;
    @TableField("complete")
    private float complete;
    @TableField("user_userID")
    private int user_userID;
    @TableField("description")
    private String description;
    @TableField("modifiedBit")
    private int modifiedBit;
    //修改位：
    //-1：从来未被匹配过（要匹配，插入记录）
    //0：最新版被匹配过，无需再匹配
    //1：被匹配过，但是修改后未被匹配过（要匹配，更新记录）
    // 当简历修改过并且修改后没有计算过匹配度，则为1。否则为0
    @TableField("rname")
    private String rname;

    @TableField("rTel")
    private String rTel;

    public void  resetResume(UserResumeEntity userResume){
        this.workplace=userResume.getWorkplace();
        this.educationlevel=userResume.getEducationlevel();
        this.age=userResume.getAge();
        this.workyear=userResume.getWorkyear();
        this.idealcity=userResume.getIdealcity();
        this.idealcareer=userResume.getIdealcareer();
        this.idealdomain=userResume.getIdealdomain();
        this.idealsalary=userResume.getIdealsalary();
        this.description=userResume.getDescription();
        this.modifiedBit=-1;
        this.rname=userResume.getRealName();
        this.rTel=userResume.getTel();
    }
}

