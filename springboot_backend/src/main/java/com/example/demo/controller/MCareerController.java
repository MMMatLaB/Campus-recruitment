package com.example.demo.controller;


import com.example.demo.common.R;
import com.example.demo.entities.*;
import com.example.demo.entities.qian.simpleSuggestCareerEntity;
import com.example.demo.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@Api(tags = {"岗位推荐"})//api中的名称
public class MCareerController {
    @Autowired
    careerService careerService;
    @Autowired
    LoginService loginService;
    @Autowired
    ResumeService resumeService;
    @Autowired
    SuggestCareerService suggestCareerService;
    @Autowired
    corporateService corporateService;
    @Autowired
    stuWantService stuWantService;
    @Autowired
    ICareerManagerService iCareerManagerService;
    @Autowired
    ICompanyManagerService iCompanyManagerService;
    @Autowired
    ICorporationManagerService iCorporationManagerService;




    @GetMapping("/company/jobs")
    public List<Career> querycareerList(@RequestParam("companyId") String keyword) {

        List<Career> CareerList = iCareerManagerService.queryCareer(keyword);
        for (Career Career : CareerList){
            System.out.println(Career);
        }
        return CareerList;

    }

    @PostMapping("/job")
    @ApiOperation("insertcareer")
    public R insertcareer(@RequestBody Career Career, HttpSession session) {
        // 前端传过来的 career 对象 判断用户名和密码是否存在
        // 将career插入数据库
        //检验格式是否正确的在前端
        System.out.println("require job");
        boolean flag=iCareerManagerService.addCareer(Career);
        if (flag) {
            return R.ok("插入成功");
        }
        else {
            return R.error("插入失败");
        }
    }

    @PutMapping("/job/{id}")
    @ApiOperation("updatecareer")
    public R updatecareer(@PathVariable int id, @RequestBody Career career, HttpSession session) {
//        Career Career=iCareerManagerService.likecareer(id);
        System.out.println("111");
        Boolean flag=iCareerManagerService.updateCareer(career);
        if (flag) {
            return R.ok("更新成功");
        }
        else {
            return R.error("更新失败");
        }
    }

    @DeleteMapping("/job/{id}")
    @ApiOperation(value = "delete")
    public R deletecareer(@PathVariable int id, HttpSession session) {
        System.out.println(id);
        System.out.println("dele");
/*        int idd = Integer.parseInt(id);
        System.out.println(idd);*/
        iCareerManagerService.delstuCareer(id);
        iCareerManagerService.delsuggestCareer(id);
        Boolean flag=iCareerManagerService.delCareer(id);
        if (flag) {
            System.out.println("su");
            return R.ok("删除成功");
        }
        else {
            System.out.println("fail");
            return R.error("删除失败");
        }
    }



    //投递简历
    @PutMapping("/wantjob")
    public R wantJob(@RequestParam String stuId,int careerId){
        int uid=loginService.selectOneByName(stuId).getUserID();
        int cid=careerId;
        ResumeEntity resume=resumeService.QueryByUserId(uid);
        if (resume==null){
            return R.error("用户没有简历");
        }
        StuWantEntity sw=stuWantService.exits(uid,cid);
        if (sw!=null){
            return R.error("您已投递过简历");
        }
        sw=new StuWantEntity(uid,cid);
        int result=stuWantService.insert(sw);
        if (result==0){
            return R.error("插入失败");
        }
        return R.ok("投递成功");
    }

    //查询岗位
    @GetMapping("/jobs")
    public List<simpleSuggestCareerEntity> queryCareers(@RequestParam(required = false) String name,@RequestParam(required = false)String workExperience,@RequestParam(required = false)String educationRequirement){
        //遍历每个专业
        List<simpleSuggestCareerEntity> simpleCareerList=new ArrayList<>();
        List<careerEntity> careerList =careerService.QueryByThree(name,workExperience,educationRequirement);
        //careerList.forEach(System.out::println);
        if(careerList==null) return null;
        for(careerEntity career:careerList) {
            simpleSuggestCareerEntity sscEntity=new simpleSuggestCareerEntity();
            sscEntity.resetCareer(career);
            sscEntity.setCompanyName(corporateService.queryName(career.getCorporationidcorporation()));
            if(career.getStatus()==1) simpleCareerList.add(sscEntity);
            //simpleCareerList.forEach(System.out::println);
        }
        return simpleCareerList;
    }



    @GetMapping("/recommand")
    @ApiOperation(value = "Recommand")
    public List<simpleSuggestCareerEntity> RecommendCareers(@RequestParam String userName){
            //根据学生信息 查询学生简历 若学生还没填写简历，则直接返回空列表
            List<simpleSuggestCareerEntity> simpleCareerList=new ArrayList<>();
            LoginEntity login=loginService.selectOneByName(userName);
            ResumeEntity resume=resumeService.QueryByUserId(login.getUserID());
            if (resume==null){
                return simpleCareerList;
            }
            // 根据学生简历 计算和每一个岗位的匹配度，插入或更新suggestCareer记录
        try{//注意返回列表非空
                //遍历每个专业
                List<careerEntity> careerList =careerService.QueryAll();
                for(careerEntity career:careerList){
                    simpleSuggestCareerEntity sscEntity=new simpleSuggestCareerEntity();
                    sscEntity.resetCareer(career);
                    //计算匹配度
                    float ratio=calculateRatio(resume,career);
                    SuggestCareerEntity suggestCareer=new SuggestCareerEntity();
                    suggestCareer.resetSC(ratio,login.getUserID(),career.getCareerID());
                    if (resume.getModifiedBit()==-1){//如果学生-岗位记录不存在：插入记录
                        suggestCareerService.insert(suggestCareer);
                    }else if (resume.getModifiedBit()==1){//如果学生-岗位记录存在，更新记录
                        suggestCareerService.update(suggestCareer);
                    }
                    //该career所属公司的名称 匹配度
                    sscEntity.setRatio(ratio);
                    sscEntity.setCompanyName(corporateService.queryName(career.getCorporationidcorporation()));
                    simpleCareerList.add(sscEntity);
            }
            resume.setModifiedBit(0);
            resumeService.update(resume);
        }catch (Exception e){
            log.error("匹配专业异常", e);
        }
        return simpleCareerList;
    }


    //推荐算法，用于计算 学生的个人简历（resume）和所给的工作岗位（career）的匹配度
    public float calculateRatio(ResumeEntity resume,careerEntity career){
        // 设置字段权重
        float workplaceWeight = 0.2f;
        float educationLevelWeight = 0.15f;
        float workYearWeight = 0.15f;
        float salaryRangeWeight = 0.2f;
        float careerInfoWeight = 0.3f;
        // 计算匹配度得分
        float workplaceScore = resume.getWorkplace().equalsIgnoreCase(career.getWorkplace()) ? 1.0f : 0.0f;
        float educationLevelScore = resume.getEducationlevel().equalsIgnoreCase(career.getEducationlevel()) ? 1.0f : 0.0f;
        float workYearScore = resume.getWorkyear().equalsIgnoreCase(career.getWorkyear()) ? 1.0f : 0.0f;
        // 计算薪资范围匹配度
        float idealSalaryMin = Float.parseFloat(resume.getIdealsalary());
        float idealSalaryMax = Float.parseFloat(resume.getIdealsalary()) + 5000; // 假设有5000的薪资浮动
        float salaryMin = Float.parseFloat(career.getSalarymin());
        float salaryMax = Float.parseFloat(career.getSalarymax());
        float salaryRangeScore = calculateOverlapRatio(idealSalaryMin, idealSalaryMax, salaryMin, salaryMax);
        // 计算职业信息匹配度
        float careerInfoScore = calculateTextSimilarity(resume.getIdealdomain(), career.getCareerInfo());
        // 综合计算总体匹配度得分
        float totalScore = workplaceWeight * workplaceScore
                + educationLevelWeight * educationLevelScore
                + workYearWeight * workYearScore
                + salaryRangeWeight * salaryRangeScore
                + careerInfoWeight * careerInfoScore;
        return totalScore;
    }
    // 计算两个数值范围的重叠度
    private float calculateOverlapRatio(float range1Min, float range1Max, float range2Min, float range2Max) {
        float overlapMin = Math.max(range1Min, range2Min);
        float overlapMax = Math.min(range1Max, range2Max);
        if (overlapMin > overlapMax) {
            // 无重叠
            return 0.0f;
        }
        // 计算重叠区域占比
        float overlapRatio = (overlapMax - overlapMin) / (range1Max - range1Min);
        return overlapRatio;
    }
    private float calculateTextSimilarity(String text1, String text2) {
        // 这里简单地比较字符串是否相等，你可以使用更复杂的文本相似度算法
        return text1.equalsIgnoreCase(text2) ? 1.0f : 0.0f;
    }



}
