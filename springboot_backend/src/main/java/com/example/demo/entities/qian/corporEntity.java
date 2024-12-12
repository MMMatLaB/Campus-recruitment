package com.example.demo.entities.qian;

import lombok.Data;

@Data
public class corporEntity {
    private int cid;
    private String companyName;

    public corporEntity(int cid,String companyName){
        this.cid=cid;
        this.companyName=companyName;
    }
    public  corporEntity(){

    }
}
