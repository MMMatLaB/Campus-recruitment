package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.dao.noticeDao;
import com.example.demo.dao.questionDao;
import com.example.demo.entities.noticeEntity;
import com.example.demo.entities.questionEntity;
import com.example.demo.service.noticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class noticeServiceImpl implements noticeService {
    @Autowired
    noticeDao noticeDao;
    @Override
    public int deleteByuser(int uid) {
        //查询出该有该id的所有实体
        QueryWrapper<noticeEntity> qw=new QueryWrapper<>();
        qw.eq("user_userID",uid);
        qw.select("noticeID");
        List<noticeEntity> List = noticeDao.selectList(qw);
        List<Integer> uridList = List.stream()
                .map(noticeEntity::getUser_userID)
                .collect(Collectors.toList());
        // 批量删除上述列表
        uridList.forEach(System.out::println);
        int result = 0;
        if (!uridList.isEmpty()) {
            result = noticeDao.deleteBatchIds(uridList);
        }
        return result;
    }

    @Override
    public List<noticeEntity> getAllNotice() {
        List<noticeEntity> noticeEntityList=noticeDao.selectList(null);
        return noticeEntityList;
    }
}
