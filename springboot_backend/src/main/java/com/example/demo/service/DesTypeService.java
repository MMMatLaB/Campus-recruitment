package com.example.demo.service;

import com.example.demo.entities.DesTypeEntity;
import org.springframework.stereotype.Service;

@Service
public interface DesTypeService {
    public DesTypeEntity queryByName(String name);
}
