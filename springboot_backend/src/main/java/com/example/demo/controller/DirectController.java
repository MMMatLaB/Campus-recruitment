package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.entities.DirectEntity;
import com.example.demo.service.IDirectManagerService;
import com.example.demo.service.IMDRelationManagerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DirectController {
    @Autowired
    IDirectManagerService IDirectManagerService;
    @Autowired
    IMDRelationManagerService imdRelationManagerService;

    @GetMapping("/directions")
    public List<DirectEntity> queryDirectList(@RequestParam(value="direction",required = false) String direction,@RequestParam(value="major",required = false) String major) {
        List<DirectEntity> directEntityList=new ArrayList<>();
        System.out.println("direction");
        System.out.println(direction);
        System.out.println("major");
        System.out.println(major);
        if (direction.equals("") && major.equals("")) {
            System.out.println("111");
            // 如果direction和major都为空，返回全部列表
            directEntityList = IDirectManagerService.queryAllDirect();
        }
        else if (!direction.equals("") && major.equals("")) {
            System.out.println("222");
            // 如果direction不为空，而major为空，进行模糊查询
            directEntityList = IDirectManagerService.queryDirect(direction);
        }
        else if (direction.equals("") && !major.equals("")) {
            System.out.println("333");
            String[] ids=imdRelationManagerService.fromMajor(major);
            directEntityList = new ArrayList<>();
            // 如果direction为空，而major不为空，根据major查询
            for (String id : ids) {
                List<DirectEntity> entities = IDirectManagerService.queryFromMajor(id);
                directEntityList.addAll(entities);
            }
        }
        else if (!direction.equals("") && !major.equals("")){
            System.out.println("444");
            // 如果direction和major都不为空，进行模糊查询并根据major过滤结果

            String[] ids=imdRelationManagerService.fromMajor(major);
            directEntityList=new ArrayList<>();
            for (String id:ids){
                List<DirectEntity> entities=IDirectManagerService.queryBoth(id,direction);
                directEntityList.addAll(entities);
            }
//            directEntityList=IDirectManagerService.queryBoth(id,direction);

        }
        for (DirectEntity directEntity : directEntityList){
            System.out.println(directEntity);
        }
        return directEntityList;

    }

    @PostMapping("/direction")
    @ApiOperation("insertDirect")
    public R insertDirect(@RequestBody DirectEntity directEntity, HttpSession session) {
        // 前端传过来的 role 对象 判断用户名和密码是否存在
        // 将role插入数据库
        //检验格式是否正确的在前端
        boolean flag=IDirectManagerService.addDirect(directEntity);
        if (flag) {
            return R.ok("插入成功");
        }
        else {
            return R.error("插入失败");
        }
    }
    @DeleteMapping("/direction/{id}")
    @ApiOperation("deleteDirect")
    public R deleteDirect(@PathVariable int id, HttpSession session) {
        Boolean flag=IDirectManagerService.delDirect(id);
        if (flag) {
            return R.ok("删除成功");
        }
        else {
            return R.error("删除失败");
        }
    }

}
