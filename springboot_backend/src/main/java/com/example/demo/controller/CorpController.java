package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.entities.Corporation;
//import com.example.demo.entities.RoleEntity;
//import com.example.demo.entities.corporateEntity;
import com.example.demo.service.ICorporationManagerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CorpController {
    @Autowired
    ICorporationManagerService iCorporationManagerService;


    @GetMapping("/corps")
    public List<Corporation> querycorpList() {
        List<Corporation> corporateEntityList = iCorporationManagerService.queryCorporation();
        return corporateEntityList;

    }

    @PostMapping("/corp")
    @ApiOperation("insertCorp")
    public R insertCrop(@RequestBody Corporation corporation, HttpSession session) {
        // 前端传过来的 role 对象 判断用户名和密码是否存在
        // 将role插入数据库
        //检验格式是否正确的在前端
        boolean flag=iCorporationManagerService.addCorporation(corporation);
        if (flag) {
            return R.ok("插入成功");
        }
        else {
            return R.error("插入失败");
        }
    }

    @PutMapping("/corp/{id}")
    @ApiOperation("updateCorp")
    public R updateCrop(@PathVariable String id, @RequestBody Corporation corporation, HttpSession session) {
//        RoleEntity roleEntity=roleManagerService.likeRole(id);
//        System.out.println(role);
        Boolean flag=iCorporationManagerService.updateCorporation(corporation);
        if (flag) {
            return R.ok("更新成功");
        }
        else {
            return R.error("更新失败");
        }
    }

    @DeleteMapping("/corp/{id}")
    @ApiOperation("deleteCrop")
    public R deleteCrop(@PathVariable int id, HttpSession session) {
        Boolean flag=iCorporationManagerService.delCorporation(id);
        if (flag) {
            return R.ok("删除成功");
        }
        else {
            return R.error("删除失败");
        }
    }


}
