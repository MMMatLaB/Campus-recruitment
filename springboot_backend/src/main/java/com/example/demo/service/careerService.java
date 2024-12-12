package com.example.demo.service;

import com.example.demo.entities.careerEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface careerService {
    public List<careerEntity> QueryAll();

    public List<careerEntity> QueryByCoId(int coId);

    public List<careerEntity> QueryByThree(String name,String workExperience,String educationRequirement);
}
