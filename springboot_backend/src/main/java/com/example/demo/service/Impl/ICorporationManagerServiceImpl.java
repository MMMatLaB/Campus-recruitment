package com.example.demo.service.Impl;


import com.example.demo.dao.ICorporationDao;
import com.example.demo.entities.Corporation;
import com.example.demo.service.ICorporationManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICorporationManagerServiceImpl implements ICorporationManagerService {
    @Autowired
    ICorporationDao iCorporationDao;

    @Override
    public List<Corporation> queryCorporation() {
        List<Corporation> corporationList=iCorporationDao.queryCorporation();
        return corporationList;
    }

    @Override
    public Boolean addCorporation(Corporation corporation) {
        Boolean flag=iCorporationDao.addCorporation(corporation);
        return flag;
    }

    @Override
    public Boolean delCorporation(int id) {
        Boolean flag=iCorporationDao.delCorporation(id);
        return flag;
    }

    @Override
    public Boolean updateCorporation(Corporation corporation) {
        Boolean flag=iCorporationDao.updateCorporation(corporation);
        return flag;
    }
}
