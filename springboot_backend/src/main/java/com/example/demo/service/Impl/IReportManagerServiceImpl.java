package com.example.demo.service.Impl;

import com.example.demo.dao.IReportManagerDao;
import com.example.demo.entities.Report;
import com.example.demo.service.IReportManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IReportManagerServiceImpl implements IReportManagerService {
    @Autowired
    IReportManagerDao iReportManagerDao;

    @Override
    public Boolean addReport(Report report) {
        Boolean flag=iReportManagerDao.addReport(report);
        return flag;
    }


}
