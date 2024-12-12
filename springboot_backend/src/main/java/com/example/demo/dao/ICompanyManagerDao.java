package com.example.demo.dao;

import com.example.demo.entities.Company;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ICompanyManagerDao {
    public List<Company> queryCompany();



    public Boolean addCompany(Company company);


    public Boolean delCompany(int id);

    public Boolean updateCompany(Company company);
}
