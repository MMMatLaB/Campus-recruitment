package com.example.demo.service;

import com.example.demo.entities.RoleEntity;

import java.util.List;

public interface IRoleManagerService {
    public List<RoleEntity> queryRole();
    public Boolean addRole(RoleEntity roleEntity);


    public Boolean delRole(int id);


    public Boolean updateRole(RoleEntity roleEntity);


    public RoleEntity likeRole(int id);

}
