package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


@Data
public class MDRelationName {
    private int majormid;
    private String majorName;
    private int directdirectID;
    private String directName;
//    @TableId(value = "MDid",type = IdType.AUTO)
    private int MDid;
}
