package com.example.demo.service;

import cn.hutool.log.Log;
import com.example.demo.entities.LoginEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface LoginService {

    public LoginEntity findUserByNameAndPasswd(LoginEntity loginEntity);

    public  int insert(LoginEntity loginEntity);

    public int update(LoginEntity loginEntity);

    /*public void queryIdAndName(LoginEntity loginEntity,list<T> m);*/

    public List<LoginEntity> queryIdAndName(LoginEntity login, List<Integer> m,List<Integer> c,int roleid) ;

    public  int Delete(int userid);

    public LoginEntity selectOneByName(String userName);

    public LoginEntity selectOneByID(int uid);
}
