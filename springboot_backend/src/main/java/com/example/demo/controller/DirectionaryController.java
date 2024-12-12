package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.entities.DictionaryEntity;
import com.example.demo.service.IDictionaryManagerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class DirectionaryController {
    @Autowired
    IDictionaryManagerService iDictionaryManagerService;

    @GetMapping("/dicts")
    public List<DictionaryEntity> queryDictionaryList() {
        List<DictionaryEntity> dictionaryEntityList = iDictionaryManagerService.queryDict();
        for (DictionaryEntity dictionaryEntity : dictionaryEntityList){
            System.out.println(dictionaryEntity);
        }
        return dictionaryEntityList;

    }

    @PostMapping("/dict")
    @ApiOperation("insertDict")
    public R insertRole(@RequestBody DictionaryEntity dictionaryEntity, HttpSession session) {
        // 前端传过来的 role 对象 判断用户名和密码是否存在
        // 将role插入数据库
        //检验格式是否正确的在前端
        boolean flag=iDictionaryManagerService.addDict(dictionaryEntity);
        if (flag) {
            return R.ok("插入成功");
        }
        else {
            return R.error("插入失败");
        }
    }
    @DeleteMapping("/dict/{id}")
    @ApiOperation("deleteDict")
    public R deleteRole(@PathVariable int id, HttpSession session) {
        Boolean flag=iDictionaryManagerService.delDict(id);
        if (flag) {
            return R.ok("删除成功");
        }
        else {
            return R.error("删除失败");
        }
    }

    @PostMapping("/dict/put")
    @ApiOperation("updateRole")
    public R updateRole(@RequestBody DictionaryEntity dictionaryEntity, HttpSession session) {

        Boolean flag=iDictionaryManagerService.updateDict(dictionaryEntity);
        if (flag) {
            return R.ok("更新成功");
        }
        else {
            return R.error("更新失败");
        }
    }

}
