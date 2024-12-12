package com.example.demo.service;

import com.example.demo.entities.MajorEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface majorService {
    public List<MajorEntity> queryByMajorName(String majorName);

    public String queryName(int mid);

    public MajorEntity queryByName(String name);



}
