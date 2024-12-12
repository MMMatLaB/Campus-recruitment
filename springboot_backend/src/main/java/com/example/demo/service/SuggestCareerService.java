package com.example.demo.service;

import com.example.demo.entities.SuggestCareerEntity;
import org.springframework.stereotype.Service;

@Service
public interface SuggestCareerService {
    public int deleteByuser(int uid);

    public int insert(SuggestCareerEntity suggestCareerEntity);

    public int update(SuggestCareerEntity suggestCareerEntity);
}
