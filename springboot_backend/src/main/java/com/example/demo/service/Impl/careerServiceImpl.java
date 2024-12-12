package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.dao.careerDao;
import com.example.demo.entities.careerEntity;
import com.example.demo.service.careerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class careerServiceImpl implements careerService {
    @Autowired
    careerDao careerDao;

    @Override
    public List<careerEntity> QueryAll() {
        List<careerEntity> careerList = careerDao.selectList(null);
        return careerList;
    }

    @Override
    public List<careerEntity> QueryByCoId(int coId) {
        QueryWrapper<careerEntity> qw = new QueryWrapper<>();
        qw.eq("corporationidcorporation", coId);
        List<careerEntity> list = careerDao.selectList(qw);
        return list;
    }

    @Override
    public List<careerEntity> QueryByThree(String name, String workExperience, String educationRequirement) {
        QueryWrapper<careerEntity> qw = new QueryWrapper<>();
        if (!name.equals("")) {
            qw.like("careerName",name);
        }
        if (!workExperience.equals("")) {
            qw.like("workyear",workExperience);
        }
        if (!educationRequirement.equals("")) {
            qw.like("educationlevel",educationRequirement);
        }
        if (name.equals("")&&workExperience.equals("")&&educationRequirement.equals("")){
            List<careerEntity> list=careerDao.selectList(null);
            return list;
        }
        List<careerEntity> list=careerDao.selectList(qw);
        return list;
    }
}
