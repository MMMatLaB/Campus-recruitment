package com.example.demo.service;

import com.example.demo.entities.Company;

import java.util.List;

public interface ICompanyManagerService {
    public List<Company> queryCompany();



    public Boolean addCompany(Company company);


    public Boolean delCompany(int id);

    public Boolean updateCompany(Company company);
}
