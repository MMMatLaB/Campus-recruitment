package com.example.demo.service;

import com.example.demo.entities.UserRoleEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserRoleService {
    public int insert(UserRoleEntity userRoleEntity);
    public int deleteByID(UserRoleEntity userRoleEntity);

    public int deleteByUser(int uid);

    public UserRoleEntity queryByUid(int uid);
}
