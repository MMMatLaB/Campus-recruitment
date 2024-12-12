package com.example.demo.dao;


import com.example.demo.entities.RoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IRoleManagerDao extends BaseMapper<RoleEntity> {
    public List<RoleEntity> queryRole();



    public Boolean addRole(RoleEntity roleEntity);


    public Boolean delRole(int id);


    public Boolean updateRole(RoleEntity roleEntity);


    public RoleEntity likeRole(int id);
}
