package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.entities.DirectEntity;
import com.example.demo.entities.MDRelationName;
import com.example.demo.entities.MajorDirectRelationEntity;
import com.example.demo.service.IMDRelationManagerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MDRelationController {
    @Autowired
    IMDRelationManagerService imdRelationManagerService;

    @GetMapping("/relations")
    public List<MDRelationName> queryRoleList(@RequestParam(value="direction",required = false) String direction, @RequestParam(value="major",required = false) String major) {

        List<MDRelationName> MDRelationNameList = null;
        if (direction.equals("") && major.equals("")) {
            System.out.println("111");
            // 如果direction和major都为空，返回全部列表
            MDRelationNameList=imdRelationManagerService.queryMDRelation();
        }
        else if (!direction.equals("") && major.equals("")) {
            System.out.println("222");
            // 如果direction不为空，而major为空，进行模糊查询
//            directEntityList = IDirectManagerService.queryDirect(direction);
            MDRelationNameList=imdRelationManagerService.queryMDRelationfromdirect(direction);
            
        }
        else if (direction.equals("") && !major.equals("")) {
            System.out.println("333");
            String[] ids=imdRelationManagerService.fromMajor(major);
            MDRelationNameList = new ArrayList<>();
            // 如果direction为空，而major不为空，根据major查询
            for (String id : ids) {
                List<MDRelationName> entities = imdRelationManagerService.queryMDRelationfromdirect(id);
                MDRelationNameList.addAll(entities);
                
            }
        }
        else if (!direction.equals("") && !major.equals("")){
            System.out.println("444");
            // 如果direction和major都不为空，进行模糊查询并根据major过滤结果

            String[] ids=imdRelationManagerService.fromMajor(major);
            MDRelationNameList=imdRelationManagerService.queryMDRelationfromboth(major,direction);
//            directEntityList=IDirectManagerService.queryBoth(id,direction);

        }
        for (MDRelationName majorDirectRelationEntity : MDRelationNameList){
            System.out.println(majorDirectRelationEntity);
        }
        return MDRelationNameList;

    }

    @PostMapping("/relation")
    @ApiOperation("insertRelation")
    public R insertDirection(@RequestBody MajorDirectRelationEntity majorDirectRelationEntity, HttpSession session) {
        System.out.println("!11");
        System.out.println(majorDirectRelationEntity);
        boolean flag=imdRelationManagerService.addMDRelation(majorDirectRelationEntity);
        if (flag) {
            return R.ok("插入成功");
        }
        else {
            return R.error("插入失败");
        }
    }

/*    @PutMapping("/relation/major/{mid}/direction/{did}")
    @ApiOperation("updateRelation")
    public R updateRole(@PathVariable int mid, @PathVariable int did,@RequestBody MajorDirectRelationEntity majorDirectRelationEntity, HttpSession session) {
//        RoleEntity roleEntity=roleManagerService.likeRole(id);

        Boolean flag=imdRelationManagerService.updateMDRelation(majorDirectRelationEntity, mid, did);
        if (flag) {
            return R.ok("更新成功");
        }
        else {
            return R.error("更新失败");
        }
    }*/

    @DeleteMapping("/relation/major/{mid}/direction/{did}")
    @ApiOperation("deleteRelation")
    public R deleteDirection(@PathVariable String mid, @PathVariable String did, HttpSession session) {

        String[] ids=imdRelationManagerService.likeMDRelation(mid,did);
        Boolean flag=true;
        for (String id:ids){
            flag=imdRelationManagerService.delMDRelation(id);
        }
//        Boolean flag=imdRelationManagerService.delMDRelation(id);
        if (flag) {
            return R.ok("删除成功");
        }
        else {
            return R.error("删除失败");
        }
    }

}
