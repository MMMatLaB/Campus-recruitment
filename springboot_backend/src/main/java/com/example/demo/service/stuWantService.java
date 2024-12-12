package com.example.demo.service;

import com.example.demo.entities.StuWantEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface stuWantService {
    public int deleteByuser(int uid);

    public int insert(StuWantEntity stuWant);

    public List<StuWantEntity> queryByCID(int cid);

    public StuWantEntity exits(int uid,int cid);
}

