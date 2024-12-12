package com.example.demo.service;

import com.example.demo.entities.*;

import java.util.List;

public interface IMajorManagerService {
    public List<MajorEntity> queryMajor(String keyword);
    public List<MajorEntity> queryAllMajor();
    public List<MajorIndustry> queryMajorIndustry(String majorid);
    public List<MajorPlace> queryMajorPlace(String majorid);

    public List<MajorCompany> queryMajorCompany(String majorid);
    public List<MajorDirectSalary> queryMajorDirectSalary(String majorid);
    public List<MajorScale> queryMajorScale(String majorid);

    public List<MajorMarket> queryMajorMarket(String majorid);
    public List<MajorGraduateGone> queryMajorGraduate(String majorid,String year);
    public List<IndustryGraduate> queryIndustryGraduate(String year);
    public List<Integer> queryYear();
    public List<GraduatePlace> queryGraduatePlace(String year);
    public List<MajorSalary> queryMajorYearSalary(String year);
    public Boolean addMajor(MajorEntity majorEntity);


    public Boolean delMajor(int id);
    public String queryMajorName(String majorid);
}
