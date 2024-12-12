package com.example.demo.service;

import com.example.demo.entities.MDRelationName;
import com.example.demo.entities.MajorDirectRelationEntity;

import java.util.List;

public interface IMDRelationManagerService {

    public List<MDRelationName> queryMDRelation();

    public List<MDRelationName> queryMDRelationfrommajor(String majorid);

    public List<MDRelationName> queryMDRelationfromdirect(String directid);
    public List<MDRelationName> queryMDRelationfromboth(String majorid,String directid);


    public Boolean addMDRelation(MajorDirectRelationEntity majorDirectRelationEntity);


    public Boolean delMDRelation(String id);

    public String[] likeMDRelation(String mid,String did);

    public String[] fromMajor(String mid);

//    public Boolean updateMDRelation(MajorDirectRelationEntity majorDirectRelationEntity,int mid,int did);
}
