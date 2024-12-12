package com.example.demo.service.Impl;

import com.example.demo.dao.whereDao;
import com.example.demo.entities.whereEntity;
import com.example.demo.service.whereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class whereServiceImpl implements whereService {
    @Autowired
    whereDao whereDao;
    @Override
    public int insert(whereEntity w) {
        int result=whereDao.insert(w);
        return result;
    }
}
