package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("corporation")
public class corporateEntity {
    @TableId(value = "idcorporation",type = IdType.AUTO)
    private int idcorporate;

    @TableField("CorpName")
    private String CorpName;

    @TableField("capacity")
    private String capacity;

    @TableField("domainn")
    private String domain;

    @TableField("BreifInfo")
    private String BreifInfo;


}
