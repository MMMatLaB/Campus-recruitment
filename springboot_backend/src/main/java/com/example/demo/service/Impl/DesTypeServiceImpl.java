package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.dao.DesTypeDao;
import com.example.demo.entities.DesTypeEntity;
import com.example.demo.service.DesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DesTypeServiceImpl implements DesTypeService {
    @Autowired
    DesTypeDao desTypeDao;
    @Override
    public DesTypeEntity queryByName(String name) {
        QueryWrapper<DesTypeEntity> qw=new QueryWrapper<>();
        qw.eq("typeName",name);
        DesTypeEntity desType=desTypeDao.selectOne(qw);
        return desType;
    }
}
