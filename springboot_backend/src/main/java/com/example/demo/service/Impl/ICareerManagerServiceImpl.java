package com.example.demo.service.Impl;

import com.example.demo.dao.ICareerManagerDao;
import com.example.demo.entities.Career;
import com.example.demo.service.ICareerManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICareerManagerServiceImpl implements ICareerManagerService {
    @Autowired
    ICareerManagerDao iCareerManagerDao;

    @Override
    public List<Career> queryCareer(String keyword) {

        List<Career> careerList=iCareerManagerDao.queryCareer(keyword);
        for (Career Career : careerList){
            System.out.println(Career);
        }
        return careerList;
    }
    @Override
    public  Boolean delsuggestCareer(int careerid){
        Boolean flag=iCareerManagerDao.delsuggestCareer(careerid);
        return flag;
    }
    @Override
    public  Boolean delstuCareer(int careerid){
        Boolean flag=iCareerManagerDao.delstuCareer(careerid);
        return flag;
    }

    @Override
    public Boolean addCareer(Career career) {
        Boolean flag=iCareerManagerDao.addCareer(career);
        return flag;
    }

    @Override
    public Boolean delCareer(int id) {
        Boolean flag=iCareerManagerDao.delCareer(id);
        return flag;
    }

    @Override
    public Boolean updateCareer(Career career) {
        Boolean flag=iCareerManagerDao.updateCareer(career);
        return flag;
    }
}
