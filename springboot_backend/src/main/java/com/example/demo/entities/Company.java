package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("company")
public class Company {
    @TableId(value = "companyid",type = IdType.AUTO)
    private int companyid;
    private String companyname;
    private String numberr;
    private String directdirectid;
    private String datee;


}
