package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("major_has_direct")
public class MajorDirectRelationEntity {

    private int majormid;
    private int directdirectID;
    @TableId(value = "MDid",type = IdType.AUTO)
    private int MDid;

}
