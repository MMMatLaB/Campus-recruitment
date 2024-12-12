package com.example.demo.dao;

import com.example.demo.entities.MDRelationName;
import com.example.demo.entities.MajorDirectRelationEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface IMDRelationManagerDao {
    public List<MDRelationName> queryMDRelation();

    public List<MDRelationName> queryMDRelationfrommajor(String majorid);

    public List<MDRelationName> queryMDRelationfromdirect(String directid);
    public List<MDRelationName> queryMDRelationfromboth(String majorid,String directid);



    public Boolean addMDRelation(MajorDirectRelationEntity majorDirectRelationEntity);


    public Boolean delMDRelation(String id);

//    public Boolean updateMDRelation(MajorDirectRelationEntity majorDirectRelationEntity);

    public String[] likeMDRelation(String mid,String did);

    public String[] fromMajor(String mid);
}
