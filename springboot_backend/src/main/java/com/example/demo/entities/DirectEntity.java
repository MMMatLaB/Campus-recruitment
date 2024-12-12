package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("direct")
public class DirectEntity {
    @TableId(value = "directid",type = IdType.AUTO)
    private int directid;
    private String directname;
}
