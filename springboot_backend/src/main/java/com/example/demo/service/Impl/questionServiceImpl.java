package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.dao.questionDao;
import com.example.demo.dao.reportDao;
import com.example.demo.entities.questionEntity;
import com.example.demo.entities.reportEntity;
import com.example.demo.service.questionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class questionServiceImpl implements questionService {
    @Autowired
    questionDao  questionDao;
    @Override
    public int deleteByuser(int uid) {
        //查询出该有该id的所有实体
        QueryWrapper<questionEntity> qw=new QueryWrapper<>();
        qw.eq("user_userID",uid);
        qw.select("idquestionnaire");
        List<questionEntity> List = questionDao.selectList(qw);
        List<Integer> uridList=new ArrayList<>();
        for (questionEntity q:List){
            int id=q.getIdquestionnaire();
            uridList.add(id);
        }
        // 批量删除上述列表
        uridList.forEach(System.out::println);
        int result = 0;
        if (!uridList.isEmpty()) {
            result = questionDao.deleteBatchIds(uridList);
        }
        return result;
    }
}
