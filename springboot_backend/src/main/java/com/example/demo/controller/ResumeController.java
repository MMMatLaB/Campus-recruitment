package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.entities.LoginEntity;
import com.example.demo.entities.ResumeEntity;
import com.example.demo.entities.StuWantEntity;
import com.example.demo.entities.careerEntity;
import com.example.demo.entities.qian.UserResumeEntity;
import com.example.demo.entities.qian.jobSeekerEntity;
import com.example.demo.service.LoginService;
import com.example.demo.service.ResumeService;
import com.example.demo.service.careerService;
import com.example.demo.service.stuWantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@Api(tags = {"简历"})//api中的名称
public class ResumeController {
    @Autowired
    ResumeService resumeService;
    @Autowired
    LoginService loginService;

    @Autowired
    stuWantService stuWantService;
    @Autowired
    careerService careerService;

    //新增第一份个人简历
    //前端处理如果新增过了就不能新增
    @PostMapping("/resume")
    @ApiOperation(value = "新增")
    public R insertResume(@RequestBody UserResumeEntity userResume){
        ResumeEntity resume=new ResumeEntity();
        resume.resetResume(userResume);
        resume.setUser_userID(loginService.selectOneByName(userResume.getUserID()).getUserID());
        int result=resumeService.insert(resume);
        if(result==0){
            R r=R.error("插入失败");
            return r;
        }
        R r=R.ok("插入成功");
        return r;
    }

    //更新个人简历
    @PutMapping("/resume/{id}")
    @ApiOperation(value = "update")
    public R updateResume(@PathVariable String id,@RequestBody UserResumeEntity userResume){
        LoginEntity login=loginService.selectOneByName(id);
        ResumeEntity resume=resumeService.QueryByUserId(login.getUserID());
        resume.resetResume(userResume);
        resume.setModifiedBit(1);
        int result=resumeService.update(resume);
        if (result==0){
            R r=R.error("更新失败");
            return r;
        }
        R r=R.ok("更新成功");
        return r;
    }

    //获取简历
    @GetMapping("/resume")
    public ResumeEntity getMyResume(@RequestParam String userName){
        LoginEntity login=loginService.selectOneByName(userName);
        ResumeEntity resume=resumeService.QueryByUserId(login.getUserID());
        if (resume==null) return null;
        //TODO bug
        return resume;
    }
    //获取求职者
    @GetMapping("/employees")
    public List<jobSeekerEntity> getJobSeekers(@RequestParam int corporateId){
        List<jobSeekerEntity> seekers=new ArrayList<>();
        //该公司的职业集合
        List<careerEntity> careers=careerService.QueryByCoId(corporateId);
        if (careers==null) {
            System.out.println("该公司暂无职业发布");
            return null;
        }
        for (careerEntity career:careers){
            //该职业的stuWant实体集合
            List<StuWantEntity> stuWantList=stuWantService.queryByCID(career.getCareerID());
            for (StuWantEntity stuWant:stuWantList){
                //每一个login实体,及其简历
                System.out.println("33333333333"+stuWant);
                LoginEntity login=loginService.selectOneByID(stuWant.getUseruserID());
                ResumeEntity resume=resumeService.QueryByUserId(stuWant.getUseruserID());
                System.out.println("1111111111111111"+login);
                System.out.println("22222222"+resume);
                jobSeekerEntity seeker=new jobSeekerEntity(career.getCareerName(),resume,login);
                seekers.add(seeker);
            }
        }
        return  seekers;
    }




}
