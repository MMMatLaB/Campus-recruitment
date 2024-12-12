package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.entities.LoginEntity;
import com.example.demo.entities.*;
import com.example.demo.entities.qian.Login;
import com.example.demo.service.LoginService;
import com.example.demo.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
@Api(tags = {"测试1"})//api中的名称
public class LoginController {

    private static final int STU=0;
    private static final int EDU=1;
    private static final int HR=2;
    @Autowired
    LoginService loginService;
    @Autowired
    UserRoleService userRoleService;

    @GetMapping("/test")
    @ApiOperation(value = "测试2")
    public R test() {
        System.out.println("Hello World");
        return R.ok("测试成功");
    }

    @PostMapping("/login")
    @ApiOperation(value = "测试2")
    public R login(@RequestBody Login login){
        //查询用户是否存在 账户密码是否匹配
        LoginEntity loginier=new LoginEntity(login.getAccount(),login.getPassword());
        System.out.println("111111111111111"+loginier);
        LoginEntity user=loginService.findUserByNameAndPasswd(loginier);
        //System.out.println("0error!!!!!!");
        //判断是否登录成功
        if (user==null){
           // System.out.println("error!!!!!!");
            return R.error("用户不存在或密码错误");
        }
        Map<String, Object> map=new HashMap<>();
        System.out.println("2error!!!!!!"+user);
        int rid=userRoleService.queryByUid(user.getUserID()).getRid();
        map.put("roleID",rid);
        map.put("ID",user.getUserName());
        map.put("realName",user.getRealName());
        map.put("mID",user.getMajormid());
        map.put("cID",user.getCid());
        R r=R.ok("登录成功");
        r.setData(map);
        //r.setResult(user);
       return r;
    }

    @PostMapping("/studentUser")
    @ApiOperation(value = "")
    public R insertStu(@RequestBody LoginEntity loginer){
        loginer.setCid(-1);
        LoginEntity user=new LoginEntity();
        user.resetLoginEntity(loginer);
        int result0=loginService.insert(user);
        UserRoleEntity userRoleEntity=new UserRoleEntity();
        userRoleEntity.setRid(STU);
        userRoleEntity.setUid(user.getUserID());
        int result1=userRoleService.insert(userRoleEntity);
        if (result0==0||result1==0){
            R r=R.error("插入失败");
        }
        R r=R.ok("插入成功");
        return r;
    }

    @PostMapping("/schoolUser")
    @ApiOperation(value = "")
    public R insertEDU(@RequestBody LoginEntity loginer) {
        loginer.setMajormid(-1);
        loginer.setCid(-1);
        LoginEntity user=new LoginEntity();
        user.resetLoginEntity(loginer);
        int result0=loginService.insert(user);
        System.out.println("1111111111111111"+result0);
        UserRoleEntity userRoleEntity=new UserRoleEntity();
        userRoleEntity.setRid(EDU);
        userRoleEntity.setUid(user.getUserID());
        int result1=userRoleService.insert(userRoleEntity);
        if (result0==0||result1==0){
            R r=R.error("插入失败");
            return r;
        }
        R r=R.ok("插入成功");
        return r;
    }
    @PostMapping("/companyUser")
    @ApiOperation(value = "")
    public R insertHR(@RequestBody LoginEntity loginer){
        loginer.setMajormid(-1);
        LoginEntity user=new LoginEntity();
        user.resetLoginEntity(loginer);
        int result0=loginService.insert(user);
        UserRoleEntity userRoleEntity=new UserRoleEntity();
        userRoleEntity.setRid(HR);
        userRoleEntity.setUid(user.getUserID());
        int result1=userRoleService.insert(userRoleEntity);
        if (result0==0||result1==0){
            R r=R.error("插入失败");
        }
        R r=R.ok("插入成功");
        return r;
    }
}