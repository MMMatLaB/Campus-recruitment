package com.example.demo.controller;


import com.example.demo.common.R;
import com.example.demo.entities.MajorEntity;
import com.example.demo.entities.corporateEntity;
import com.example.demo.entities.qian.EduEntity;
import com.example.demo.entities.qian.HREntity;
import com.example.demo.entities.LoginEntity;
import com.example.demo.entities.qian.StuEntity;
import com.example.demo.entities.qian.UserEntity;
import com.example.demo.service.LoginService;
import com.example.demo.service.SuggestCareerService;
import com.example.demo.service.UserRoleService;
import com.example.demo.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
@Api(tags = {"改查删用户"})//api中的名称
public class userController {
    @Autowired
    LoginService loginService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    majorService  majorService;
    @Autowired
    SuggestCareerService suggestCareerService;
    @Autowired
    stuWantService stuWantService;
    @Autowired
    ResumeService resumeService;
    @Autowired
    reportService reportService;
    @Autowired
    questionService questionService;
    @Autowired
    noticeService noticeService;
    @Autowired
    corporateService corporateService;

    public R updateUser(LoginEntity login){
        int result=loginService.update(login);
        if (result==0){
            R r=R.error("更新失败");
        }
        R r=R.ok("更新成功");
        return r;
    }

    //修改学生信息：仅允许修改除id以外的数据
    @PutMapping("/studentUser")
    @ApiOperation(value = "update")
    public R updateStu(@RequestBody StuEntity stu){
        LoginEntity login=loginService.selectOneByName(stu.getStuId());;
        MajorEntity majorEntity=majorService.queryByName(stu.getMajor());
        login.setMajormid(majorEntity.getMid());
        login.resetStu(stu);
        return updateUser(login);
    }
    //修改教务
    @PutMapping("/schoolUser")
    @ApiOperation(value = "update")
    public R updateEDU(@RequestBody EduEntity edu){
        LoginEntity login=loginService.selectOneByName(edu.getEduId());
        System.out.println("11111"+login);
        System.out.println("2222"+edu);
        login.resetEdu(edu);
        return updateUser(login);
    }
    //修改HR
    @PutMapping("/companyUser")
    @ApiOperation(value = "update")
    public R updateHR(@RequestBody HREntity HR){
        LoginEntity login=loginService.selectOneByName(HR.getHRId());
        System.out.println("11111111"+HR);
        System.out.println("11111111"+login);
        corporateEntity c= corporateService.queryByCName(HR.getCompany()).get(0);
        login.setCid(c.getIdcorporate());
        login.resetHR(HR);
        return updateUser(login);
    }


    //修改个人信息
    @PutMapping("/personalInfo")
    @ApiOperation(value = "updatePersonal")
    public R updatePersonal(@RequestBody UserEntity user){
        LoginEntity login=loginService.selectOneByName(user.getId());
        login.resetLoginInfoUser(user);
        int result=loginService.update(login);
        if (result==0){
            R r=R.error("更新失败");
            return r;
        }
        Map<String, Object> map=new HashMap<>();
        map.put("id",user.getId());
        map.put("name",user.getName());
        map.put("gender",user.getGender());
        map.put("major0rCompany",user.getMajor0rCompany());
        map.put("passwd",user.getPasswd());
        map.put("tel",user.getTel());
        map.put("email",user.getEmail());
        R r=R.ok("更新成功");
        r.setData(map);
        return r;
    }
    //查询展示个人信息
    @GetMapping("/personalInfo")
    @ApiOperation(value = "queryPersonal")
    public UserEntity queryPersonal(@RequestParam String userName){
        UserEntity user=new UserEntity();
        try {
            LoginEntity login=loginService.selectOneByName(userName);
            user.resetPartInfo(login);
            //查询出专业或公司
            if (login.getMajormid()!=-1){
                user.setMajor0rCompany(majorService.queryName(login.getMajormid()));
            }
            if (login.getCid()!=-1){
                user.setMajor0rCompany(corporateService.queryName(login.getCid()));
            }
            return user;
        }catch (Exception e) {
            log.error("查询异常", e);
            return user;
        }

    }


    //查询学生：学号，realname，专业
    @GetMapping("/studentUsers")
    @ApiOperation(value = "query")
    public List<StuEntity> QueryStu(@RequestParam(required = false) String userName, @RequestParam(required = false) String realName, @RequestParam(required = false) String major){
        LoginEntity user=new LoginEntity();
        user.setUserName(userName);
        user.setRealName(realName);
        List<MajorEntity> MidList=majorService.queryByMajorName(major);
        List<Integer> midList=new ArrayList<>();
        if (MidList!=null){
            for (MajorEntity m:MidList){
                int mid=m.getMid();
                midList.add(mid);
            }
        }
        List<Integer> cList=null;
        List<LoginEntity> list=loginService.queryIdAndName(user,midList,cList,0);
        //List<StuEntity> stu=
        //要求遍历list中的每一个成员：将其userName赋值给stu的stu
        // 假设存在名为StuEntity的类
        List<StuEntity> stuList = new ArrayList<>();
        if (list==null){
            return stuList;
        }
        for (LoginEntity loginEntity : list) {
            StuEntity stu = new StuEntity();
            stu.setStuId(loginEntity.getUserName());
            stu.setName(loginEntity.getRealName());
            stu.setTel(loginEntity.getTelephone());
            stu.setStatus(loginEntity.getStatus());
            stu.setMajor(majorService.queryName(loginEntity.getMajormid()));
            stuList.add(stu);
        }
        return stuList;
    }
    //查询教务
    @GetMapping("/schoolUsers")
    @ApiOperation(value = "query")
    public List<EduEntity> QueryEdu(@RequestParam(required = false) String userName, @RequestParam(required = false) String realName){
        LoginEntity user=new LoginEntity();
        user.setUserName(userName);
        user.setRealName(realName);
        List<Integer> List=null;
        List<Integer> cList=null;
        List<LoginEntity> list=loginService.queryIdAndName(user,List,cList,1);
        List<EduEntity> EduList = new ArrayList<>();
        if (list==null){
            return EduList;
        }
        for (LoginEntity loginEntity : list) {
            EduEntity Edu = new EduEntity();
            Edu.setEduId(loginEntity.getUserName());
            Edu.setName(loginEntity.getRealName());
            Edu.setTel(loginEntity.getTelephone());
            Edu.setStatus(loginEntity.getStatus());
            EduList.add(Edu);
        }
        return EduList;
    }
    //查询HR
    @GetMapping("/companyUsers")
    @ApiOperation(value = "query")
    public List<HREntity> QueryHR(@RequestParam(required = false) String userName, @RequestParam(required = false) String realName, @RequestParam(required = false) String company){
        LoginEntity user=new LoginEntity();
        user.setUserName(userName);
        user.setRealName(realName);
        List<Integer> List=null;
        List<corporateEntity> CList=corporateService.queryByCName(company);
        List<Integer> cList=new ArrayList<>();
        if (CList!=null){
            for (corporateEntity c:CList){
                int i=c.getIdcorporate();
                cList.add(i);
            }
        }
        List<LoginEntity> list=loginService.queryIdAndName(user,List,cList,2);
        List<HREntity> HrList = new ArrayList<>();
        if (list==null){
            return HrList;
        }
        for (LoginEntity loginEntity : list) {
            HREntity HR = new HREntity();
            HR.setHRId(loginEntity.getUserName());
            HR.setName(loginEntity.getRealName());
            HR.setTel(loginEntity.getTelephone());
            HR.setStatus(loginEntity.getStatus());
            HR.setCompany(corporateService.queryName(loginEntity.getCid()));
            HrList.add(HR);
        }
        return HrList;
    }






    public R delete(String userName){
        try {
            // 批量删除用户
            LoginEntity login=loginService.selectOneByName(userName);
            int userIds=login.getUserID();
            ArrayList<Integer> results=new ArrayList<>();
            int result=userRoleService.deleteByUser(userIds);
            results.add(result);
            int result1=suggestCareerService.deleteByuser(userIds);
            results.add(result1);
            int result2=stuWantService.deleteByuser(userIds);
            results.add(result2);
            int result3=resumeService.deleteByuser(userIds);
            results.add(result3);
            int result4=reportService.deleteByuser(userIds);
            results.add(result4);
            int result5=questionService.deleteByuser(userIds);
            results.add(result5);
            /*int result6=noticeService.deleteByuser(userIds);
            results.add(result6);*/
            int result7=loginService.Delete(userIds);
            results.add(result7);
            for (int i=0;i<5;i++){
                if (result<0) return R.error("删除异常");
            }
            return R.ok("批量删除成功");
        } catch (Exception e) {
            log.error("批量删除异常", e);
            return R.error("批量删除异常");
        }
    }
    //删除学生
    @DeleteMapping("/studentUser")
    @ApiOperation(value = "delete")
    public R deleteStu(@RequestParam String userName) {
        return delete(userName);
    }
    //删除教务
    @DeleteMapping("/schoolUser")
    @ApiOperation(value = "delete")
    public R deleteEdu(@RequestParam String userName) {
        return delete(userName);
    }
    //批量删除HR
    @DeleteMapping("/companyUser")
    @ApiOperation(value = "delete")
    public R deleteHR(@RequestParam String userName) {
        return delete(userName);
    }




}
