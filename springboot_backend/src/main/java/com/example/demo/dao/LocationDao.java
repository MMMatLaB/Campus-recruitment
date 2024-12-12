package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entities.LocationEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDao extends BaseMapper<LocationEntity> {
}
