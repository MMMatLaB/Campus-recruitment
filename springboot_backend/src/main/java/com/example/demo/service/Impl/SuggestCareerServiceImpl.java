package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.dao.suggestCareerDao;
import com.example.demo.entities.SuggestCareerEntity;
import com.example.demo.entities.UserRoleEntity;
import com.example.demo.entities.questionEntity;
import com.example.demo.service.SuggestCareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SuggestCareerServiceImpl implements SuggestCareerService {
    @Autowired
    suggestCareerDao suggestCareerDao;
    @Override
    public int deleteByuser(int uid) {
        //查询出该有该id的所有实体
        QueryWrapper<SuggestCareerEntity> qw=new QueryWrapper<>();
        qw.eq("user_userID", uid);
        qw.select("suggestID");
        List<SuggestCareerEntity> List = suggestCareerDao.selectList(qw);
        List<Integer> uridList=new ArrayList<>();
        for (SuggestCareerEntity q:List){
            int id=q.getSuggestID();
            uridList.add(id);
        }
        // 批量删除上述列表
        uridList.forEach(System.out::println);
        int result = 0;
        if (!uridList.isEmpty()) {
            result = suggestCareerDao.deleteBatchIds(uridList);
        }
        return result;
    }

    @Override
    public int insert(SuggestCareerEntity suggestCareerEntity) {
        suggestCareerDao.insert(suggestCareerEntity);
        return 0;
    }

    @Override
    public int update(SuggestCareerEntity suggestCareerEntity) {
        suggestCareerDao.updateById(suggestCareerEntity);
        return 0;
    }
}
