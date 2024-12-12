package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.dao.stuWantDao;
import com.example.demo.entities.StuWantEntity;
import com.example.demo.entities.questionEntity;
import com.example.demo.service.stuWantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StuWantServiceImpl implements stuWantService {
    @Autowired
    stuWantDao stuWantDao;
    @Override
    public int deleteByuser(int uid) {
        //查询出该有该id的所有实体
        QueryWrapper<StuWantEntity> qw=new QueryWrapper<>();
        qw.eq("user_userID", uid);
        qw.select("wantID");
        List<StuWantEntity> List = stuWantDao.selectList(qw);
        List<Integer> uridList=new ArrayList<>();
        for (StuWantEntity q:List){
            int id=q.getWantID();
            uridList.add(id);
        }
        // 批量删除上述列表
        uridList.forEach(System.out::println);
        int result = 0;
        if (!uridList.isEmpty()) {
            result = stuWantDao.deleteBatchIds(uridList);
        }
        return result;
    }

    @Override
    public int insert(StuWantEntity stuWant) {
        int result=stuWantDao.insert(stuWant);
        return result;
    }

    @Override
    public List<StuWantEntity> queryByCID(int cid) {
        QueryWrapper<StuWantEntity> qw=new QueryWrapper<>();
        qw.eq("career_careerID1",cid);
        List<StuWantEntity> list=stuWantDao.selectList(qw);
        return list;
    }

    @Override
    public StuWantEntity exits(int uid, int cid) {
        QueryWrapper<StuWantEntity> qw=new QueryWrapper<>();
        qw.eq("user_userID",uid);
        qw.eq("career_careerID1",cid);
        StuWantEntity sw=stuWantDao.selectOne(qw);
        return sw;
    }
}
