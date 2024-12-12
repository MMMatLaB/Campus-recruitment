package com.example.demo.entities;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("corporation")
public class Corporation {
    @TableId(value = "idcorporation",type = IdType.AUTO)
    private int idcorporation;
    private String CorpName;
    private String capacity;
    private String domainn;
    private String BreifInfo;
}
