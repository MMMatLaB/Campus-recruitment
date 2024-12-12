package com.example.demo.service;

import com.example.demo.entities.noticeEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface noticeService {
    public int deleteByuser(int uid);

    public List<noticeEntity> getAllNotice();
}
