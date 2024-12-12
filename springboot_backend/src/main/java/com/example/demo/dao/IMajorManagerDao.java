package com.example.demo.dao;

import com.example.demo.entities.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IMajorManagerDao {
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
    public List<MajorSalary> queryMajorYearSalary(String year);
    public List<GraduatePlace> queryGraduatePlace(String year);
    public List<Integer> queryYear();
    public Boolean addMajor(MajorEntity majorEntity);

    public String queryMajorName(String majorid);


    public Boolean delMajor(int id);

    public Boolean delMajorHasDirect(int id);

    public Boolean delMajorAvgSalary(int id);


}
