package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("graduate")
public class graduateEntity {
    @TableId(value = "graduateID",type = IdType.AUTO)
    private int graduateID;
    @TableField("UniqueId")
    private String UniqueId;
    @TableField("name")
    private String name;
    @TableField("year")
    private String year;
    @TableField("gender")
    private String gender;
    @TableField("major_mid")
    private int majormid;
    @TableField("where_wid")
    private int wherewid;

    public graduateEntity(String uniqueId,String name,String year,String gender,int mid,int wid){
        this.UniqueId=uniqueId;
        this.name=name;
        this.year=year;
        this.gender=gender;
        this.majormid=mid;
        this.wherewid=wid;
    }
    public graduateEntity(){

    }
}
