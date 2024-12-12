package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entities.UserRoleEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDao extends BaseMapper<UserRoleEntity> {
}
