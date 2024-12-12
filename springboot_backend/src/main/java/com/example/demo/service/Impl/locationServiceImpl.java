package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.dao.LocationDao;
import com.example.demo.entities.LocationEntity;
import com.example.demo.service.locationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class locationServiceImpl implements locationService {
    @Autowired
    LocationDao locationDao;
    @Override
    public LocationEntity queryByCity(String city) {
        QueryWrapper<LocationEntity> qw=new QueryWrapper<>();
        qw.eq("city",city);
        LocationEntity location=locationDao.selectOne(qw);
        return location;
    }
}
