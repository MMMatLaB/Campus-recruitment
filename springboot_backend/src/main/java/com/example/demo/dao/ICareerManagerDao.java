package com.example.demo.dao;


import com.example.demo.entities.Career;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ICareerManagerDao {

    public List<Career> queryCareer(String keyword);
    public  Boolean delsuggestCareer(int careerid);
    public  Boolean delstuCareer(int careerid);



    public Boolean addCareer(Career career);


    public Boolean delCareer(int id);

    public Boolean updateCareer(Career career);
}
