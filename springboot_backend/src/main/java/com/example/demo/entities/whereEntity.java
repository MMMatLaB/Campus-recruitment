package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("nali")
public class whereEntity {
    @TableId(value = "wid",type = IdType.AUTO)
    private int wid;

    @TableField("location_locationID")
    private int locatId;

    @TableField("destinationType_typeID")
    private int DtypeId;

    @TableField("company")
    private  String company;

    @TableField("industry")
    private  String industry;

    @TableField("studySituation")
    private  String studySituation;



    public whereEntity(int locatId,int D,String company,String industry,String studySituation){
        this.locatId=locatId;
        this.DtypeId=D;
        this.company=company;
        this.industry=industry;
        this.studySituation=studySituation;
    }

    public whereEntity(){

    }
}
