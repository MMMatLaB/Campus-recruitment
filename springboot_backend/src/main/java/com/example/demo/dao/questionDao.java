package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entities.questionEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface questionDao extends BaseMapper<questionEntity> {
}
