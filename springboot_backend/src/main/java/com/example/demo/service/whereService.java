package com.example.demo.service;

import com.example.demo.entities.whereEntity;
import org.springframework.stereotype.Service;

@Service
public interface whereService {
    public int insert(whereEntity w);
}
