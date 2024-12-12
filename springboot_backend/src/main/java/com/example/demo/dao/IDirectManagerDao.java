package com.example.demo.dao;

import com.example.demo.entities.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface IDirectManagerDao extends BaseMapper<DirectEntity> {
    public List<DirectEntity> queryDirect(String keyword);

    public List<DirectEntity> queryAllDirect();

    public List<DirectEntity> queryFromMajor(String directiondirectionid);

    public List<DirectNumber> queryDirectCapacity(int directdirectID);

    public List<DirectEntity> queryBoth(String directdirectid,String keyword);

    public List<DirectSalary> queryDirectSalary(String directid);

    public List<DirectIndustry> queryDirectIndustry(String directid);

    public List<DirectScale> queryDirectScale(String directid);
    public List<DirectTag> queryDirectTag(String directid);
    public List<DirectCompany> quertDirectCompany(String directid);
    public Boolean addDirect(DirectEntity directEntity);


    public Boolean delDirect(int id);

    public Boolean delDirectAvgSalary(int id);
    public Boolean delDirectHasDirect (int id);
    public Boolean delDirectHasIndustry (int id);
}
