package com.example.demo.entities.qian;

import com.example.demo.entities.LoginEntity;
import com.example.demo.entities.ResumeEntity;
import lombok.Data;

@Data
public class jobSeekerEntity {
    private String stuID;
    private String stuName;
    private String careerName;
    private String workyear;
    private String educationlevel;
    private String age;

    public jobSeekerEntity(String careerName,ResumeEntity resume, LoginEntity login){
        this.stuID=login.getUserName();
        this.stuName=login.getRealName();
        this.careerName=careerName;
        this.workyear=resume.getWorkyear();
        this.educationlevel=resume.getEducationlevel();
        this.age=resume.getAge();


    }
    public jobSeekerEntity(){

    }
}
