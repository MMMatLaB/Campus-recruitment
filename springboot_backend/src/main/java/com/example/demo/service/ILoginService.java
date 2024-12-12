package com.example.demo.service;

import com.example.demo.entities.LoginEntity;


public interface ILoginService {
    public LoginEntity findUserByUsernameAndPwd(LoginEntity loginEntity);
}
