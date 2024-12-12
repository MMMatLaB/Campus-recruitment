package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.utils;
import com.example.demo.dao.LoginDao;
import com.example.demo.dao.UserRoleDao;
import com.example.demo.entities.LoginEntity;
import com.example.demo.entities.UserRoleEntity;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.scanner.Scanner;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginDao loginDao;
    @Autowired
    UserRoleDao userRoleDao;
    @Override
    public LoginEntity findUserByNameAndPasswd(LoginEntity loginEntity) {
        QueryWrapper<LoginEntity> qw=new QueryWrapper<>();
        System.out.println("zheli"+loginEntity);
        System.out.println("shishoi"+loginEntity.getUserName());
        qw.eq("userName",loginEntity.getUserName());
        qw.eq("password",loginEntity.getPassword());
        qw.eq("status",1);
        LoginEntity user=loginDao.selectOne(qw);
        System.out.println("zhe"+user);
        return user;
    }

    @Override
    public int insert(LoginEntity loginEntity) {
        loginEntity.setCreated_time(utils.time());
        LoginEntity login=selectOneByName(loginEntity.getUserName());
        if (login!=null) return 0;
        int result = loginDao.insert(loginEntity);
        //System.out.println("result:"+result);
        return result;
    }

    @Override
    public int update(LoginEntity loginEntity) {
        int result=loginDao.updateById(loginEntity);
        return result;
    }

    @Override
    public List<LoginEntity> queryIdAndName(LoginEntity login, List<Integer> m,List<Integer> c,int roleId) {
        QueryWrapper<LoginEntity> qwUser=new QueryWrapper<>();
        boolean a=!login.getUserName().equals("");
        boolean b=!login.getRealName().equals("");
        if (a){
            System.out.println("111!!!!!!!!!!!!!!!!!!!!!!!");
            qwUser.like("userName",login.getUserName());//学号
        }
        if (b){
            System.out.println("222!!!!!!!!!!!!!!!!!!!!!!!");
            qwUser.like("realName",login.getRealName());//姓名
        }
        if (m!=null&& !m.isEmpty()){
            System.out.println("333!!!!!!!!!!!!!!!!!!!!!!!");
            qwUser.in("major_mid",m);
        }
        if (c!=null&& !c.isEmpty()){
            System.out.println("4444!!!!!!!!!!!!!!!!!!!!!!!");
            qwUser.in("corporation_idcorporation",c);
        }
        List<LoginEntity> users;
        if ((!a)&&(!b)&&!(m!=null&& !m.isEmpty())&&!(c!=null&& !c.isEmpty())){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
             users=loginDao.selectList(null);
             users.forEach(System.out::println);
            Iterator<LoginEntity> iterator = users.iterator();
            while (iterator.hasNext()) {
                LoginEntity loginEntity = iterator.next();
                QueryWrapper<UserRoleEntity> qw=new QueryWrapper<>();
                qw.eq("user_userID1",loginEntity.getUserID());
                UserRoleEntity ur=userRoleDao.selectOne(qw);
                //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!"+ur);o

                int rid=ur.getRid();
                if (rid!=roleId) {
                    iterator.remove(); // 使用迭代器的 remove 方法安全地移除元素

                }
            }
            return users;
        }
        System.out.println("111111111!!!!!!!!!!!!!!!!!!!!!!!");
        users=loginDao.selectList(qwUser);
        users.forEach(System.out::println);
        Iterator<LoginEntity> iterator = users.iterator();
        while (iterator.hasNext()) {
            LoginEntity loginEntity = iterator.next();
            QueryWrapper<UserRoleEntity> qw=new QueryWrapper<>();
            qw.eq("user_userID1",loginEntity.getUserID());

            int rid=userRoleDao.selectOne(qw).getRid();
            if (rid!=roleId) {
                iterator.remove(); // 使用迭代器的 remove 方法安全地移除元素

            }
        }
        users.forEach(System.out::println);
        return users;
    }

    @Override
    public int Delete(int userid) {
        QueryWrapper<LoginEntity> QW=new QueryWrapper<>();
        QW.eq("userID",userid);
        LoginEntity login=loginDao.selectOne(QW);
        int result=loginDao.deleteById(login);
        return result;
    }

    @Override
    public LoginEntity selectOneByName(String userName) {
        QueryWrapper<LoginEntity> qwUser=new QueryWrapper<>();
        qwUser.eq("userName",userName);
        LoginEntity login=loginDao.selectOne(qwUser);
        return login;
    }

    @Override
    public LoginEntity selectOneByID(int uid) {
        LoginEntity login=loginDao.selectById(uid);
        return login;
    }


}
