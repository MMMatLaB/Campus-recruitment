package com.example.demo.service;

import com.example.demo.entities.reportEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface reportService {
    public int deleteByuser(int uid);

    public List<reportEntity> QueryAll();

    public List<reportEntity> queryAll1();

    public int updateStatus(reportEntity reportEntity,String status);

    public reportEntity queryById(int rid);
}
