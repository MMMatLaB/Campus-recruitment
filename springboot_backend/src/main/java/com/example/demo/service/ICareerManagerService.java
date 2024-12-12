package com.example.demo.service;

import com.example.demo.entities.Career;

import java.util.List;

public interface ICareerManagerService {
    public List<Career> queryCareer(String keyword);
    public  Boolean delsuggestCareer(int careerid);
    public  Boolean delstuCareer(int careerid);



    public Boolean addCareer(Career career);


    public Boolean delCareer(int id);

    public Boolean updateCareer(Career career);
}
