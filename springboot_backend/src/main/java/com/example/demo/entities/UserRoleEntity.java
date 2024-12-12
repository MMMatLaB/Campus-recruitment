package com.example.demo.entities;




import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_has_role")
public class UserRoleEntity {

    @TableId("URid")
    private int URid;
    @TableField("user_userID1")
    private int uid;
    @TableField("role_idrole")
    private int rid;


}
