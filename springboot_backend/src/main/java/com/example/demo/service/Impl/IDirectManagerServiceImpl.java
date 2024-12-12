package com.example.demo.service.Impl;

import com.example.demo.dao.IDirectManagerDao;
import com.example.demo.entities.*;
import com.example.demo.service.IDirectManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IDirectManagerServiceImpl implements IDirectManagerService {
    @Autowired
    IDirectManagerDao IDirectManagerDao;

    @Override
    public List<DirectEntity> queryDirect(String keyword) {
        System.out.println("normal");
        List<DirectEntity> directList=IDirectManagerDao.queryDirect(keyword);
        return directList;

    }

    @Override
    public Boolean addDirect(DirectEntity directEntity) {
        Boolean flag=IDirectManagerDao.addDirect(directEntity);
        return flag;
    }

    @Override
    public Boolean delDirect(int id) {
        IDirectManagerDao.delDirectHasDirect(id);
        IDirectManagerDao.delDirectAvgSalary(id);
        IDirectManagerDao.delDirectHasIndustry(id);
        Boolean flag=IDirectManagerDao.delDirect(id);
        return flag;
    }
    @Override
    public List<DirectEntity> queryAllDirect(){
        System.out.println("all");
        List<DirectEntity> directEntityList=IDirectManagerDao.queryAllDirect();
        return directEntityList;
    }

    @Override
    public List<DirectEntity> queryFromMajor(String directiondirectionid){
        System.out.println("major");
        List<DirectEntity> id=IDirectManagerDao.queryFromMajor(directiondirectionid);
        return id;

    }
    @Override
    public List<DirectEntity> queryBoth(String directdirectid,String keyword){
        System.out.println("both");
        List<DirectEntity> directEntityList=IDirectManagerDao.queryBoth(directdirectid,keyword);
        return directEntityList;
    }

    @Override
    public List<DirectNumber> queryDirectCapacity(int directdirectID){
        List<DirectNumber> directNumberList=IDirectManagerDao.queryDirectCapacity(directdirectID);
        return directNumberList;
    }

    @Override
    public List<DirectSalary> queryDirectSalary(String directid){
        List<DirectSalary> directSalaryList=IDirectManagerDao.queryDirectSalary(directid);
        return directSalaryList;
    }

    @Override
    public List<DirectIndustry> queryDirectIndustry(String directid){
        System.out.println(directid);
        List<DirectIndustry> directIndustryList=IDirectManagerDao.queryDirectIndustry(directid);
        for (DirectIndustry directIndustry:directIndustryList){
            System.out.println(directIndustry);
        }
        return directIndustryList;
    }
    @Override
    public List<DirectScale> queryDirectScale(String directid){
        List<DirectScale> directScaleList=IDirectManagerDao.queryDirectScale(directid);
        return directScaleList;
    }

    @Override
    public List<DirectTag> queryDirectTag(String directid){
        List<DirectTag> directTagList=IDirectManagerDao.queryDirectTag(directid);
        return directTagList;
    }

    @Override
    public List<DirectCompany> quertDirectCompany(String directid){
        List<DirectCompany> directCompanyList=IDirectManagerDao.quertDirectCompany(directid);
        return directCompanyList;
    }
}
