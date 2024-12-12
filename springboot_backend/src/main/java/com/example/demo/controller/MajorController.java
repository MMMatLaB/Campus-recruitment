package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.entities.MajorEntity;
import com.example.demo.service.IMajorManagerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class MajorController {
    @Autowired
    IMajorManagerService IMajorManagerService;
    @GetMapping("/majors")
    public List<MajorEntity> queryMajorList(@RequestParam(value="major",required = false) String keyword) {

        List<MajorEntity> majorEntityList;
        if (keyword.equals("")){
            majorEntityList=IMajorManagerService.queryAllMajor();
        }
        else {
            majorEntityList = IMajorManagerService.queryMajor(keyword);
        }
        for (MajorEntity majorEntity : majorEntityList){
            System.out.println(majorEntity);
        }
        return majorEntityList;

    }

    @GetMapping("/majorname")
    public String queryMajorName(@RequestParam String majorid){
        String majorName= IMajorManagerService.queryMajorName(majorid);
        return majorName;
    }

//    @GetMapping("/statistics/majorindustry")
//    public List<MajorIndustry> queryMajorIndustry(@RequestParam String majorid){
//        System.out.println(majorid);
//        List<MajorIndustry> majorIndustryList=IMajorManagerService.queryMajorIndustry(majorid);
//        for (MajorIndustry majorIndustry:majorIndustryList){
//            System.out.println(majorIndustry);
//        }
//        return majorIndustryList;
//    }

    @PostMapping("/major")
    @ApiOperation("insertMajor")
    public R insertMajor(@RequestBody MajorEntity majorEntity, HttpSession session) {
        // 前端传过来的 role 对象 判断用户名和密码是否存在
        // 将role插入数据库
        //检验格式是否正确的在前端
        boolean flag=IMajorManagerService.addMajor(majorEntity);
        if (flag) {
            return R.ok("插入成功");
        }
        else {
            return R.error("插入失败");
        }
    }
    @DeleteMapping("/major/{id}")
    @ApiOperation("deleteMajor")
    public R deleteMajor(@PathVariable int id, HttpSession session) {
        Boolean flag=IMajorManagerService.delMajor(id);
        if (flag) {
            return R.ok("删除成功");
        }
        else {
            return R.error("删除失败");
        }
    }

}
