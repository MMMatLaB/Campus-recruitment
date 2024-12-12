package com.example.demo.service;

import com.example.demo.entities.graduateEntity;
import org.springframework.stereotype.Service;

@Service
public interface graduateService {
    public int insert(graduateEntity g);
}
