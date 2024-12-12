package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("role")
public class RoleEntity {
    @TableId(value = "idrole",type = IdType.AUTO)
    private int  idrole;
    @TableField("rolename")
    private String rolename;
    @TableField("createtime")
    private String createtime;
    @TableField("status")
    private int status;


    public RoleEntity() {
    }

    public RoleEntity(int id, String rolename, String created_time) {
        this.idrole = id;
        this.rolename=rolename;
        this.createtime=created_time;
    }
/*    @Override
    public String toString() {
        return "Role{" +
                "id=" + idrole +
                ", rolename='" + rolename + '\'' +
                ", created_time='" + createtime + '\'' +
                '}';
    }*/

}


