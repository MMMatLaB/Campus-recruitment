package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entities.LoginEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao extends BaseMapper<LoginEntity> {
}