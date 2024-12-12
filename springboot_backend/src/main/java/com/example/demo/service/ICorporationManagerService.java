package com.example.demo.service;

import com.example.demo.entities.Corporation;

import java.util.List;

public interface ICorporationManagerService {
    public List<Corporation> queryCorporation();



    public Boolean addCorporation(Corporation corporation);


    public Boolean delCorporation(int id);

    public Boolean updateCorporation(Corporation corporation);
}
