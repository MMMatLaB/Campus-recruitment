package com.example.demo.entities.qian;

import com.example.demo.entities.careerEntity;
import lombok.Data;

@Data
public class simpleSuggestCareerEntity {
    private int cid;
    private String careerName;
    private String workplace;
    private String educationlevel;
    private String workyear;

    //匹配度:来自SuggestCareer
    private float ratio;
    private String careerInfo;
    //公司名称
    private String companyName;

    private String salaryMax;
    private String salaryMin;

    public void resetCareer(careerEntity career){
        this.cid=career.getCareerID();
        this.careerName=career.getCareerName();
        this.workplace=career.getWorkplace();
        this.educationlevel=career.getEducationlevel();
        this.workyear=career.getWorkyear();
        this.careerInfo=career.getCareerInfo();
        this.salaryMax=career.getSalarymax();
        this.salaryMin=career.getSalarymin();

    }

}
