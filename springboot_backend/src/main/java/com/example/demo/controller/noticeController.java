package com.example.demo.controller;


import com.example.demo.entities.noticeEntity;
import com.example.demo.service.noticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@Api(tags = {"通知"})//api中的名称
public class noticeController {
    @Autowired
    noticeService noticeService;

    //查看通知列表 与 详情
    @GetMapping("/notices")
    @ApiOperation(value = "getNotice")
    private List<noticeEntity> getAllNotices(){
        List<noticeEntity> noticeList=noticeService.getAllNotice();
        return noticeList;
    }


}
