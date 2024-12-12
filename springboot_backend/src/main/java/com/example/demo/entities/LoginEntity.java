package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.entities.qian.EduEntity;
import com.example.demo.entities.qian.HREntity;
import com.example.demo.entities.qian.StuEntity;
import com.example.demo.entities.qian.UserEntity;
import lombok.Data;

@Data
@TableName("user")
public class LoginEntity {

    @TableId(value = "userID",type = IdType.AUTO)
    private int userID;
    @TableField("userName")
    private String userName;
    @TableField("password")
    private String password;
    @TableField("created_time")
    private String created_time;
    @TableField("status")
    private int status;
    @TableField("email")
    private String email;
    @TableField("telephone")
    private String telephone;
    @TableField("gender")
    private String gender;
    @TableField("realName")
    private String realName;
    @TableField("major_mid")
    private int majormid;
    @TableField("corporation_idcorporation")
    private int cid;

    public LoginEntity(String userNAME,String pass){
        this.userName=userNAME;
        this.password=pass;
    }

    public void resetLoginEntity(LoginEntity loginEntity) {
        this.userName = loginEntity.getUserName();
        this.password = loginEntity.getPassword();
        this.status = loginEntity.getStatus();
        this.email = loginEntity.getEmail();
        this.telephone = loginEntity.getTelephone();
        this.gender = loginEntity.getGender();
        this.realName = loginEntity.getRealName();
        this.majormid = loginEntity.getMajormid();
        this.cid = loginEntity.getCid();
    }

    public void resetLoginInfoUser(UserEntity user){
        this.setPassword(user.getPasswd());
        this.setTelephone(user.getTel());
        this.setEmail(user.getEmail());
    }

    public void resetStu(StuEntity stu){
        this.userName=stu.getStuId();
        this.realName=stu.getName();
        this.telephone=stu.getTel();
        this.status=stu.getStatus();
        //this.major_mid=
    }
    public void resetEdu(EduEntity edu){
        this.userName= edu.getEduId();
        this.realName=edu.getName();
        this.telephone=edu.getTel();
        this.status=edu.getStatus();
        //this.major_mid=
    }
    public void resetHR(HREntity hr){
        this.userName= hr.getHRId();
        this.realName=hr.getName();
        this.telephone=hr.getTel();
        this.status=hr.getStatus();
        //this.major_mid=
    }
    public LoginEntity() {
    }
}
