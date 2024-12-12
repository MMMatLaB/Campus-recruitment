package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.entities.corporateEntity;
import com.example.demo.entities.qian.corporEntity;
import com.example.demo.service.corporateService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
@Api(tags = {"企业用户"})//api中的名称
public class CorporateController {
    @Autowired
    corporateService corporateService;

    @GetMapping("/companys")
    public List<corporEntity> getAllCorporate(){
        List<corporateEntity> c=corporateService.getAll();
        List<corporEntity> list=new ArrayList<>();
        for (corporateEntity co:c){
            corporEntity corpor=new corporEntity(co.getIdcorporate(),co.getCorpName());
            if (co.getIdcorporate()!=-1) list.add(corpor);
        }


        return list;
    }
}
