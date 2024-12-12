package com.example.demo.entities.qian;

import com.example.demo.entities.LoginEntity;
import lombok.Data;

@Data
public class UserEntity {
    private String id;
    private String name;
    private String gender;
    private String major0rCompany;
    private String passwd;
    private String tel;
    private String email;

    public int resetPartInfo(LoginEntity login){
        int result=0;
        this.setId(login.getUserName());
        this.setName(login.getRealName());
        this.setGender(login.getGender());
        this.setPasswd(login.getPassword());
        this.setTel(login.getTelephone());
        this.setEmail(login.getEmail());
        return result;
    }

}
