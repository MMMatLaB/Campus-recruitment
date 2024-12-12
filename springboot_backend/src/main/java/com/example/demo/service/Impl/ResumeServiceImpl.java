package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.dao.ResumeDao;
import com.example.demo.entities.ResumeEntity;
import com.example.demo.entities.StuWantEntity;
import com.example.demo.entities.questionEntity;
import com.example.demo.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

@Service

public class ResumeServiceImpl implements ResumeService {
    @Autowired
    ResumeDao resumeDao;
    @Override
    public int deleteByuser(int uid) {
        //查询出该有该id的所有实体
        QueryWrapper<ResumeEntity> qw=new QueryWrapper<>();
        qw.eq("user_userID",uid);
        qw.select("idResume");
        List<ResumeEntity> List = resumeDao.selectList(qw);
        List<Integer> uridList=new ArrayList<>();
        for (ResumeEntity q:List){
            int id=q.getIdResume();
            uridList.add(id);
        }
        // 批量删除上述列表
        uridList.forEach(System.out::println);
        int result = 0;
        if (!uridList.isEmpty()) {
            result = resumeDao.deleteBatchIds(uridList);
        }
        return result;
    }

    @Override
    public ResumeEntity QueryByUserId(int uid) {
        QueryWrapper<ResumeEntity> qw=new QueryWrapper<>();
        qw.eq("user_userID",uid);
        ResumeEntity resume=resumeDao.selectOne(qw);
        if (resume==null) return null;
        return resume;
    }

    @Override
    public int update(ResumeEntity resume) {
        int result=resumeDao.updateById(resume);
        return result;
    }

    @Override
    public int updateModified(ResumeEntity resume) {
        // 创建UpdateWrapper对象，设置更新条件
        UpdateWrapper<ResumeEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("idResume", resume.getIdResume()); // 假设idResume是主键
        // 设置要更新的字段值
        updateWrapper.set("modifiedBit", resume.getModifiedBit());
        // 调用update方法执行更新操作
        int affectedRows = resumeDao.update(null, updateWrapper);
        return affectedRows;
    }

    @Override
    public int insert(ResumeEntity resume) {
        int result=resumeDao.insert(resume);
        return result;
    }

    @Override
    public ResumeEntity getMy(int uid) {
        QueryWrapper<ResumeEntity> qw=new QueryWrapper<>();
        qw.eq("user_userID",uid);
        ResumeEntity resume=resumeDao.selectOne(qw);
        if (resume==null) return null;
        return resume;
    }
}
