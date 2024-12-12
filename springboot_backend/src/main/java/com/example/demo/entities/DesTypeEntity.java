package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("destinationtype")
public class DesTypeEntity {
    @TableId(value = "typeId",type = IdType.AUTO)
    private int typeId;

    @TableField("typeName")
    private String typeName;
}
