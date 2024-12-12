package com.example.demo.service.Impl;

import com.example.demo.dao.IRoleManagerDao;
import com.example.demo.entities.RoleEntity;
import com.example.demo.service.IRoleManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IRoleManagerServiceImpl implements IRoleManagerService {
    @Autowired
    IRoleManagerDao IRoleManagerDao;

    @Override
    public List<RoleEntity> queryRole() {
//        //获取sqlSession对象
////        SqlSession sqlSession = MyBatisUtils.getSqlSession();
////        String sqlId = "com.mybatis.service.IRoleManagerService.queryRole";
////        //执行sql语句
////        List<Role> roleList = sqlSession.selectList(sqlId);
////        sqlSession.commit();   //mybatis默认不会手动提交事务,在修改数据之后要 提交事务
////        //关闭连接
////        sqlSession.close();
////        return roleList;
//        SqlSession sqlSession = MyBatisUtils.getSqlSession();
//        IRoleManagerService mapper = sqlSession.getMapper(IRoleManagerService.class);
//        List<Role> roleList = mapper.queryRole();
///*        for (Role role : roleList) {
//            System.out.println(role);
//        }*/
//        sqlSession.close();


//        QueryWrapper<Role> qw = new QueryWrapper();
//        qw.eq("idrole",0);
//        qw.eq("id",12);
//        qw.eq("bane","12");
//        List<Role> roleList=IRoleManagerDao.selectList(qw);

        List<RoleEntity> roleEntityList = IRoleManagerDao.queryRole();
        System.out.println(roleEntityList);
        return roleEntityList;
    }

    @Override
    public Boolean addRole(RoleEntity roleEntity) {
//        SqlSession sqlSession = MyBatisUtils.getSqlSession();
/*        String sqlId = "com.mybatis.service.IRoleManagerService.addRole";
        //执行sql语句
        int updateCount = sqlSession.update(sqlId,role);
        sqlSession.commit();   //mybatis默认不会手动提交事务,在修改数据之后要 提交事务
        //关闭连接
        sqlSession.close();
        if(updateCount>0){
            return true;
        }else{
            return false;
        }*/
//        IRoleManagerService mapper = sqlSession.getMapper(IRoleManagerService.class);
        Boolean flag = IRoleManagerDao.addRole(roleEntity);
        return flag;
    }

    @Override
    public Boolean delRole(int id) {
        //获取sqlSession对象
/*        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        IRoleManagerService mapper = sqlSession.getMapper(IRoleManagerService.class);*/
        Boolean flag = IRoleManagerDao.delRole(id);

        return flag;

    }

    @Override
    public Boolean updateRole(RoleEntity roleEntity) {
        Boolean flag = IRoleManagerDao.updateRole(roleEntity);
//        sqlSession.commit();
//        System.out.println(flag);
//        sqlSession.close();
        return flag;

    }

    @Override
    public RoleEntity likeRole(int id) {
        RoleEntity roleEntityList = IRoleManagerDao.likeRole(id);
        return roleEntityList;
    }
}

