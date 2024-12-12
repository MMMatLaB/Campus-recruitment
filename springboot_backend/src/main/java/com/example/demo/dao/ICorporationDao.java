package com.example.demo.dao;

import com.example.demo.entities.Corporation;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ICorporationDao {
    public List<Corporation> queryCorporation();



    public Boolean addCorporation(Corporation corporation);


    public Boolean delCorporation(int id);

    public Boolean updateCorporation(Corporation corporation);
}
