package com.example.demo.service.Impl;

import com.example.demo.dao.ICompanyManagerDao;
import com.example.demo.entities.Company;
import com.example.demo.service.ICompanyManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICompanyManagerServiceImpl implements ICompanyManagerService {
    @Autowired
    ICompanyManagerDao iCompanyManagerDao;

    @Override
    public List<Company> queryCompany() {
        List<Company> companyList=iCompanyManagerDao.queryCompany();
        return companyList;
    }

    @Override
    public Boolean addCompany(Company company) {
        Boolean flag=iCompanyManagerDao.addCompany(company);
        return flag;
    }

    @Override
    public Boolean delCompany(int id) {
        Boolean flag=iCompanyManagerDao.delCompany(id);
        return flag;
    }

    @Override
    public Boolean updateCompany(Company company) {
        Boolean flag=iCompanyManagerDao.updateCompany(company);
        return flag;
    }
}
