package com.example.demo.entities;

import lombok.Data;

import java.util.List;

@Data
public class JobReport {
    private List<GraduatePlace> graduatePlaceList;
    private List<MajorGraduateGone> majorGraduateGoneList;
    private List<MajorSalary> majorSalaryList;
    private List<MajorRate> majorRateList;
    private List<IndustryGraduate> industryGraduateList;

    public List<GraduatePlace> getGraduatePlaceList() {
        return graduatePlaceList;
    }

    public void setGraduatePlaceList(List<GraduatePlace> graduatePlaceList) {
        this.graduatePlaceList = graduatePlaceList;
    }

    public List<MajorGraduateGone> getMajorGraduateGoneList() {
        return majorGraduateGoneList;
    }

    public void setMajorGraduateGoneList(List<MajorGraduateGone> majorGraduateGoneList) {
        this.majorGraduateGoneList = majorGraduateGoneList;
    }

    public List<MajorSalary> getMajorSalaryList() {
        return majorSalaryList;
    }

    public void setMajorSalaryList(List<MajorSalary> majorSalaryList) {
        this.majorSalaryList = majorSalaryList;
    }

    public List<MajorRate> getMajorRateList() {
        return majorRateList;
    }

    public void setMajorRateList(List<MajorRate> majorRateList) {
        this.majorRateList = majorRateList;
    }

    public List<IndustryGraduate> getIndustryGraduateList() {
        return industryGraduateList;
    }

    public void setIndustryGraduateList(List<IndustryGraduate> industryGraduateList) {
        this.industryGraduateList = industryGraduateList;
    }
}
