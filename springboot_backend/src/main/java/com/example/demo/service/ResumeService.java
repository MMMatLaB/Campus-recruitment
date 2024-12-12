package com.example.demo.service;

import com.example.demo.entities.ResumeEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResumeService {
    public int deleteByuser(int uid);

    public ResumeEntity QueryByUserId(int uid);

    public int update(ResumeEntity resume);
    public int updateModified(ResumeEntity resume);

    public int insert(ResumeEntity resume);

    public ResumeEntity getMy(int uid);
}
