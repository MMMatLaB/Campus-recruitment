package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dictionary")
public class DictionaryEntity {
    @TableId(value = "dictionaryid",type = IdType.AUTO)
    private int dictionaryid;
    private String dictType;
    private String dictValue;

}
