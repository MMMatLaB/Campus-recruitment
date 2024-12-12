package com.example.demo.service.Impl;

import com.example.demo.dao.graduateDao;
import com.example.demo.entities.graduateEntity;
import com.example.demo.service.graduateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class graduateServiceImpl implements graduateService {
    @Autowired
    graduateDao graduateDao;

    @Override
    public int insert(graduateEntity g) {
        int result=graduateDao.insert(g);
        return result;
    }
}
