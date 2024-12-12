package com.example.demo.service.Impl;

import com.example.demo.dao.IMDRelationManagerDao;
import com.example.demo.entities.MDRelationName;
import com.example.demo.entities.MajorDirectRelationEntity;
import com.example.demo.service.IMDRelationManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IMDRelationManagerServiceImpl implements IMDRelationManagerService {
    @Autowired
    IMDRelationManagerDao imdRelationManagerDao;
    @Override
    public List<MDRelationName> queryMDRelation() {
        List<MDRelationName> majorDirectRelationEntities=imdRelationManagerDao.queryMDRelation();
        return majorDirectRelationEntities;
    }

    @Override
    public List<MDRelationName> queryMDRelationfrommajor(String majorid){
        List<MDRelationName> majorDirectRelationEntityList=imdRelationManagerDao.queryMDRelationfrommajor(majorid);
        return majorDirectRelationEntityList;
    }
    @Override
    public List<MDRelationName> queryMDRelationfromdirect(String directid){
        List<MDRelationName> majorDirectRelationEntityList=imdRelationManagerDao.queryMDRelationfromdirect(directid);
        return majorDirectRelationEntityList;
    }
    @Override
    public List<MDRelationName> queryMDRelationfromboth(String majorid,String directid){
        List<MDRelationName> majorDirectRelationEntityList=imdRelationManagerDao.queryMDRelationfromboth(majorid,directid);
        return majorDirectRelationEntityList;
    }

    @Override
    public Boolean addMDRelation(MajorDirectRelationEntity majorDirectRelationEntity) {
        Boolean flag=imdRelationManagerDao.addMDRelation(majorDirectRelationEntity);
        return flag;
    }

    @Override
    public Boolean delMDRelation(String id) {
//        String id=imdRelationManagerDao.likeMDRelation(mid,did);
//        System.out.println("id");
//        System.out.println(id);
        Boolean flag=imdRelationManagerDao.delMDRelation(id);
/*        Boolean flag = true; // 初始化为true

        for (Integer id : ids) {
            Boolean deleteResult = imdRelationManagerDao.delMDRelation(id);
            if (!deleteResult) {
                flag = false; // 如果有任何一个删除操作失败，则将flag设置为false
                break; // 可以选择立即退出循环，或者继续遍历剩余的ids
            }
        }*/

        return flag;
    }

    @Override
    public String[] likeMDRelation(String mid,String did){
        System.out.println(mid);
        System.out.println(did);
        String[] ids=imdRelationManagerDao.likeMDRelation(mid,did);
        System.out.println("tryyy");
        System.out.println(ids);
        return ids;
    }

    @Override
    public String[] fromMajor(String mid){
        String[] allDirectinMajorList=imdRelationManagerDao.fromMajor(mid);
        return allDirectinMajorList;
    }

/*    @Override
    public Boolean updateMDRelation(MajorDirectRelationEntity majorDirectRelationEntity,int mid,int did) {
        int id=imdRelationManagerDao.likeMDRelation(mid,did);
        Boolean flag=imdRelationManagerDao.updateMDRelation(majorDirectRelationEntity);
*//*        Boolean flag = true; // 初始化为true

        for (Integer id : ids) {
            Boolean deleteResult = imdRelationManagerDao.updateMDRelation(majorDirectRelationEntity);
            if (!deleteResult) {
                flag = false; // 如果有任何一个删除操作失败，则将flag设置为false
                break; // 可以选择立即退出循环，或者继续遍历剩余的ids
            }
        }*//*
        return flag;
    }*/
}
