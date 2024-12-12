package com.example.demo.dao;

import com.example.demo.entities.Report;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface IReportManagerDao {
    public Boolean addReport(Report report);
//    public List<Report> queryReport();

}
