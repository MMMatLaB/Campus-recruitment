package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.dao.UserRoleDao;
import com.example.demo.entities.LoginEntity;
import com.example.demo.entities.MajorEntity;
import com.example.demo.entities.UserRoleEntity;
import com.example.demo.entities.questionEntity;
import com.example.demo.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleDao userRoleDao;
    @Override
    public int insert(UserRoleEntity userRoleEntity) {
        int result=userRoleDao.insert(userRoleEntity);
        return result;
    }

    @Override
    public int deleteByID(UserRoleEntity userRoleEntity) {
        int result =userRoleDao.deleteById(userRoleEntity);
        return 0;
    }

    @Override
    public int deleteByUser(int uid) {
            QueryWrapper<UserRoleEntity> qw = new QueryWrapper<>();
            // 查询出该有该id的所有实体
            qw.eq("user_userID1", uid);
            qw.select("URid");
            List<UserRoleEntity> userRoleList = userRoleDao.selectList(qw);

        List<Integer> uridList=new ArrayList<>();
        for (UserRoleEntity q:userRoleList){
            int id=q.getURid();
            uridList.add(id);
        }
            // 批量删除上述列表
            uridList.forEach(System.out::println);

            int result = 0;
            if (!uridList.isEmpty()) {
                result = userRoleDao.deleteBatchIds(uridList);
            }
            return result;
        }

    @Override
    public UserRoleEntity queryByUid(int uid) {
        QueryWrapper<UserRoleEntity> qw=new QueryWrapper<>();
        qw.eq("user_userID1",uid);
        UserRoleEntity userRole=userRoleDao.selectOne(qw);
        return userRole;
    }

}
