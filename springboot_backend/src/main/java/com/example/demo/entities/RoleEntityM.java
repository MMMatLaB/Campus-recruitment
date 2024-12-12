package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("role")
public class RoleEntityM {
    private int  idrole;
    private String rolename;
    private String created_time;
    private int status;




/*
    public RoleEntity() {
    }

    public RoleEntity(int id, String rolename, String created_time) {
        this.idrole = id;
        this.rolename=rolename;
        this.created_time=created_time;
    }
    @Override
    public String toString() {
        return "Role{" +
                "id=" + idrole +
                ", rolename='" + rolename + '\'' +
                ", created_time='" + created_time + '\'' +
                '}';
    }*/

}


