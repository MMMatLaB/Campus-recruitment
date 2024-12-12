package com.example.demo.service;

import com.example.demo.entities.*;

import java.util.List;

public interface IDirectManagerService {
    public List<DirectEntity> queryDirect(String keyword);
    public Boolean addDirect(DirectEntity directEntity);


    public Boolean delDirect(int id);
    public List<DirectEntity> queryAllDirect();
    public List<DirectEntity> queryFromMajor(String directiondirectionid);
    public List<DirectEntity> queryBoth(String directiondirectionid,String keyword);

    public List<DirectNumber> queryDirectCapacity(int directdirectID);
    public List<DirectSalary> queryDirectSalary(String directid);
    public List<DirectIndustry> queryDirectIndustry(String directid);
    public List<DirectScale> queryDirectScale(String directid);
    public List<DirectTag> queryDirectTag(String directid);
    public List<DirectCompany> quertDirectCompany(String directid);


}
