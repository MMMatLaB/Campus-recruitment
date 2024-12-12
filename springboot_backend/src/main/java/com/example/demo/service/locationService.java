package com.example.demo.service;

import com.example.demo.entities.LocationEntity;
import org.springframework.stereotype.Service;

@Service
public interface locationService {

    public LocationEntity queryByCity(String city);
}
