package com.example.demo.service;

import com.example.demo.entities.corporateEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface corporateService {

    List<corporateEntity> queryByCName(String CName);

    public String queryName(int cid);

    List<corporateEntity> getAll();

}
