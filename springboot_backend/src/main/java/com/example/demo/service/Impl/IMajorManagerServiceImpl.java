package com.example.demo.service.Impl;


import com.example.demo.dao.IMajorManagerDao;
import com.example.demo.entities.*;
import com.example.demo.service.IMajorManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IMajorManagerServiceImpl implements IMajorManagerService {
    @Autowired
    IMajorManagerDao IMajorManagerDao;

    @Override
    public String queryMajorName(String majorid){
        String majorName= IMajorManagerDao.queryMajorName(majorid);
        return majorName;
    }

    @Override
    public List<MajorEntity> queryMajor(String keyword) {
        List<MajorEntity> majorList=IMajorManagerDao.queryMajor(keyword);
        return majorList;
    }
    @Override
    public List<MajorEntity> queryAllMajor(){
        List<MajorEntity> majorEntityList=IMajorManagerDao.queryAllMajor();
        return majorEntityList;
    }

    @Override
    public List<MajorIndustry> queryMajorIndustry(String majorid){
        List<MajorIndustry> majorIndustryList=IMajorManagerDao.queryMajorIndustry(majorid);
        return majorIndustryList;
    }
    @Override
    public List<MajorPlace> queryMajorPlace(String majorid){
        List<MajorPlace> majorPlaceList=IMajorManagerDao.queryMajorPlace(majorid);
        return majorPlaceList;
    }

    @Override
    public List<MajorCompany> queryMajorCompany(String majorid){
        List<MajorCompany> majorCompanyList=IMajorManagerDao.queryMajorCompany(majorid);
        return majorCompanyList;
    }

    @Override
    public List<MajorDirectSalary> queryMajorDirectSalary(String majorid){
        List<MajorDirectSalary> majorDirectSalaryList=IMajorManagerDao.queryMajorDirectSalary(majorid);
        return majorDirectSalaryList;
    }

    @Override
    public List<MajorScale> queryMajorScale(String majorid){
        List<MajorScale> majorScaleList=IMajorManagerDao.queryMajorScale(majorid);
        return majorScaleList;
    }
    @Override
    public List<MajorMarket> queryMajorMarket(String majorid){
        List<MajorMarket> majorMarketList=IMajorManagerDao.queryMajorMarket(majorid);
        return majorMarketList;
    }

    @Override
    public List<MajorGraduateGone> queryMajorGraduate(String majorid,String year){
        List<MajorGraduateGone> majorGraduateGoneList=IMajorManagerDao.queryMajorGraduate(majorid,year);
        return majorGraduateGoneList;
    }
    @Override
    public List<MajorSalary> queryMajorYearSalary(String year){
        List<MajorSalary> majorSalaryList=IMajorManagerDao.queryMajorYearSalary(year);
        return majorSalaryList;
    }
    @Override
    public List<IndustryGraduate> queryIndustryGraduate(String year){
        List<IndustryGraduate> industryGraduateList=IMajorManagerDao.queryIndustryGraduate(year);
        return industryGraduateList;
    }

    @Override
    public List<Integer> queryYear(){
        List<Integer> yearList=IMajorManagerDao.queryYear();
        return yearList;
    }
    @Override
    public List<GraduatePlace> queryGraduatePlace(String year){
        List<GraduatePlace> graduatePlaceList=IMajorManagerDao.queryGraduatePlace(year);
        return graduatePlaceList;
    }

    @Override
    public Boolean addMajor(MajorEntity majorEntity) {
        Boolean flag=IMajorManagerDao.addMajor(majorEntity);
        return flag;
    }

    @Override
    public Boolean delMajor(int id) {
        IMajorManagerDao.delMajorAvgSalary(id);
        IMajorManagerDao.delMajorHasDirect(id);
        Boolean flag=IMajorManagerDao.delMajor(id);
        return flag;
    }
}
