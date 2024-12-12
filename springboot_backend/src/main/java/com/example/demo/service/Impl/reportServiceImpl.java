package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.dao.ResumeDao;
import com.example.demo.dao.reportDao;
import com.example.demo.entities.ResumeEntity;
import com.example.demo.entities.noticeEntity;
import com.example.demo.entities.questionEntity;
import com.example.demo.entities.reportEntity;
import com.example.demo.service.reportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class reportServiceImpl implements reportService {
    @Autowired
    reportDao reportDao;
    @Override
    public int deleteByuser(int uid) {
        //查询出该有该id的所有实体
        QueryWrapper<reportEntity> qw=new QueryWrapper<>();
        qw.eq("user_userID",uid);
        qw.select("reportID");
        List<reportEntity> List = reportDao.selectList(qw);
        List<Integer> uridList=new ArrayList<>();
        for (reportEntity q:List){
            int id=q.getReportID();
            uridList.add(id);
        };
        // 批量删除上述列表
        uridList.forEach(System.out::println);
        int result = 0;
        if (!uridList.isEmpty()) {
            result = reportDao.deleteBatchIds(uridList);
        }
        return result;
    }

    @Override
    public List<reportEntity> QueryAll() {
        List<reportEntity> List=reportDao.selectList(null);
        return List;
    }

    @Override
    public List<reportEntity> queryAll1() {
        QueryWrapper<reportEntity> qw=new QueryWrapper<>();
        qw.eq("state",1);
        List<reportEntity> list=reportDao.selectList(qw);
        return list;
    }

    @Override
    public int updateStatus(reportEntity reportEntity,String status) {
        // 创建UpdateWrapper对象，设置更新条件
        UpdateWrapper<reportEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("reportID", reportEntity.getReportID()); // 假设idResume是主键
        // 设置要更新的字段值
        updateWrapper.set("state", status);
        // 调用update方法执行更新操作
        int affectedRows = reportDao.update(null, updateWrapper);
        return affectedRows;
    }

    @Override
    public reportEntity queryById(int rid) {
        /*QueryWrapper<reportEntity> qw=new QueryWrapper<>();
        qw.eq("reportID",rid);*/
        reportEntity report=reportDao.selectById(rid);
        return report;
    }
}
