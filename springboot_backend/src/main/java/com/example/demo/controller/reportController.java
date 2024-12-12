package com.example.demo.controller;


import com.example.demo.common.R;
import com.example.demo.entities.noticeEntity;
import com.example.demo.entities.reportEntity;
import com.example.demo.service.noticeService;
import com.example.demo.service.reportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@Api(tags = {"就业质量报告"})//api中的名称
public class reportController {
    @Autowired
    reportService reportService;
    //学生查看就业质量报告 列表 与 详情
    @GetMapping("/stu/employmenReports")
    @ApiOperation(value = "getNotice")
    public List<reportEntity> getAll1Reports(){
        List<reportEntity> List=reportService.queryAll1();
        System.out.println("1111111111111111111111");
        List.forEach(System.out::println);
        return List;
    }

    //学校查看
    @GetMapping("/employmentReports")
    @ApiOperation(value = "getNotice")
    public List<reportEntity> getAllReports(){
        List<reportEntity> List=reportService.QueryAll();
        return List;
    }

    //修改状态
    @PutMapping("/employmentReportState")
    public R updateState(@RequestParam int reportID,String status){
        reportEntity report=reportService.queryById(reportID);
        int result=reportService.updateStatus(report,status);
        if (result==0){
            return R.error("更新失败");
        }
        return R.ok("更新成功");
    }

}
