package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("location")
public class LocationEntity {
    @TableId(value = "locationID",type = IdType.AUTO)
    private int locationID;

    @TableField("city")
    private String city;

    @TableField("province")
    private String province;
}
