package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.entities.RoleEntity;
import com.example.demo.service.IRoleManagerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class RoleController {

    //    @Autowired
//    roleManagerDao roleManagerDao;
    @Autowired
    IRoleManagerService roleManagerService;

    @GetMapping("/roles")
    public List<RoleEntity> queryRoleList() {
        List<RoleEntity> roleEntityList = roleManagerService.queryRole();
        for (RoleEntity roleEntity : roleEntityList){
            System.out.println(roleEntity);
        }
        return roleEntityList;

    }

    @PostMapping("/role")
    @ApiOperation("insertRole")
    public R insertRole(@RequestBody RoleEntity roleEntity, HttpSession session) {
        // 前端传过来的 role 对象 判断用户名和密码是否存在
        // 将role插入数据库
        //检验格式是否正确的在前端
        boolean flag=roleManagerService.addRole(roleEntity);
        if (flag) {
            return R.ok("插入成功");
        }
        else {
            return R.error("插入失败");
        }
    }

    @PutMapping("/role/{id}")
    @ApiOperation("updateRole")
    public R updateRole(@PathVariable int id, @RequestBody RoleEntity role, HttpSession session) {
//        RoleEntity roleEntity=roleManagerService.likeRole(id);
        System.out.println(role);
        Boolean flag=roleManagerService.updateRole(role);
        if (flag) {
            return R.ok("更新成功");
        }
        else {
            return R.error("更新失败");
        }
    }

    @DeleteMapping("/role/{id}")
    @ApiOperation("deleteRole")
    public R deleteRole(@PathVariable int id, HttpSession session) {
        Boolean flag=roleManagerService.delRole(id);
        if (flag) {
            return R.ok("删除成功");
        }
        else {
            return R.error("删除失败");
        }
    }


}
