package com.example.demo.controller;

import com.example.demo.entities.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.*;

@RestController
public class StatisticsController {
    @Autowired
    ICareerManagerService iCareerManagerService;
    @Autowired
    ICompanyManagerService iCompanyManagerService;
    @Autowired
    ICorporationManagerService iCorporationManagerService;
    @Autowired
    IDirectManagerService iDirectManagerService;

    @Autowired
    IMDRelationManagerService imdRelationManagerService;

    @Autowired
    IMajorManagerService iMajorManagerService;

    @Autowired
    IReportManagerService iReportManagerService;




    @GetMapping("/statistics/major/{id}")
    public List<DirectEntity> querycDirectList(@PathVariable String id) {
        String[] idds=imdRelationManagerService.fromMajor(id);
        List<DirectEntity> directEntityList=new ArrayList<>();
        for (String idd:idds){
            List<DirectEntity> entities=iDirectManagerService.queryFromMajor(idd);
            directEntityList.addAll(entities);
        }
        for (DirectEntity directEntity : directEntityList){
            System.out.println(directEntity);
        }
        return directEntityList;


    }


    @GetMapping("/statistics/placecapacity")
    public List<DirectNumber> querycDirectInfo(@RequestParam String id) {
        System.out.println("id");
        System.out.println(id);
        List<DirectNumber> directNumberList=iDirectManagerService.queryDirectCapacity(Integer.parseInt(id));
        for(DirectNumber directNumber:directNumberList){
            System.out.println(directNumber);
        }
        return directNumberList;
    }

    @GetMapping("/statistics/salarycapacity")
    public List<DirectSalary> queryNumSalary(@RequestParam String directid){
        System.out.println(directid);
        System.out.println("directid");
        List<DirectSalary> directSalaryList=iDirectManagerService.queryDirectSalary(directid);
        for(DirectSalary directSalary:directSalaryList){
            System.out.println(directSalary);
        }
        return directSalaryList;
    }

    @GetMapping("/statistics/industrycapacity")
    public List<DirectIndustry> queryNumIndustry(@RequestParam String directid){
        System.out.println(directid);
        System.out.println("directid");
        List<DirectIndustry> directIndustryList=iDirectManagerService.queryDirectIndustry(directid);
        for (DirectIndustry directIndustry:directIndustryList){
            System.out.println(directIndustry);
        }
        return directIndustryList;
    }

    @GetMapping("/statistics/majorindustry")
    public List<MajorIndustry> queryMajorIndustry(@RequestParam String majorid){
        System.out.println(majorid);
        List<MajorIndustry> majorIndustryList=iMajorManagerService.queryMajorIndustry(majorid);
        for (MajorIndustry majorIndustry:majorIndustryList){
            System.out.println(majorIndustry);
        }
        return majorIndustryList;
    }

    @GetMapping("/statistics/directscale")
    public List<DirectScale> queryDirectScale(@RequestParam String directid){
        List<DirectScale> directScaleList=iDirectManagerService.queryDirectScale(directid);
        return directScaleList;
    }

    @GetMapping("/statistics/majorplace")
    public  List<MajorPlace> queryMajorPlace(@RequestParam String majorid){
        List<MajorPlace> majorPlaceList=iMajorManagerService.queryMajorPlace(majorid);
        return majorPlaceList;
    }

    @GetMapping("/statistics/directcompany")
    public List<DirectCompany> queryDirectCompany(@RequestParam String directid){
        List<DirectCompany> directCompanyList=iDirectManagerService.quertDirectCompany(directid);
        return directCompanyList;
    }

    @GetMapping("/statistics/directtag")
    public List<DirectTag> queryDirectTag(@RequestParam String directid){
        List<DirectTag> directTagList=iDirectManagerService.queryDirectTag(directid);
        return directTagList;
    }

    @GetMapping("/statistics/majorcompany")
    public List<MajorCompany> queryMajorCompany(@RequestParam String majorid){
        List<MajorCompany> majorCompanyList=iMajorManagerService.queryMajorCompany(majorid);
        return majorCompanyList;
    }
    @GetMapping("/statistics/majoravgdirectsalary")
    public List<MajorDirectSalary> queryMajorDirectSalary(@RequestParam String majorid){
        System.out.println(majorid);
        System.out.println("test");
        List<MajorDirectSalary> majorDirectSalaryList=iMajorManagerService.queryMajorDirectSalary(majorid);
        for (MajorDirectSalary majorDirectSalary:majorDirectSalaryList){
            System.out.println(majorDirectSalary);
        }
        return majorDirectSalaryList;
    }

    @GetMapping("/statistics/graduateyear")
    public List<Integer> queryGraduateYear(){
        List<Integer> yearList=iMajorManagerService.queryYear();
        return yearList;
    }

    @GetMapping("/statistics/majorscale")
    public List<MajorScale> queryMajorScale(@RequestParam String majorid){
        List<MajorScale> majorScaleList=iMajorManagerService.queryMajorScale(majorid);
        return majorScaleList;
    }

    @GetMapping("/statistics/majormarket")
    public List<MajorMarket> queryMajorMarket(@RequestParam String majorid){
        List<MajorMarket> majorMarketList=iMajorManagerService.queryMajorMarket(majorid);
        return majorMarketList;
    }

    @GetMapping("/statistics/graduategone")
    public List<MajorGraduateGone> queryMajorGraduateGone(@RequestParam String year){
        System.out.println("year");
        System.out.println(year);
        List<MajorEntity> majorEntityList = iMajorManagerService.queryAllMajor();
        List<MajorGraduateGone> majorGraduateGoneList = new ArrayList<>();

        for (MajorEntity majorEntity : majorEntityList) {
            String majorId = String.valueOf(majorEntity.getMid());
            System.out.println(majorId);
            List<MajorGraduateGone> majorGraduateGones = iMajorManagerService.queryMajorGraduate(majorId,year);
            System.out.println(majorGraduateGones);
//            if (majorGraduateGones.isEmpty()) {
//                MajorGraduateGone emptyResult = new MajorGraduateGone();
//                emptyResult.setMajorName(majorEntity.getMajorName());
//                emptyResult.setGraduateCountID1(0);
//                emptyResult.setGraduateCountID2(0);
//                emptyResult.setGraduateCountID3(0);
//                majorGraduateGones.add(emptyResult);
//            }
            majorGraduateGoneList.addAll(majorGraduateGones);
        }
        for (MajorGraduateGone majorGraduateGone:majorGraduateGoneList){
            System.out.println(majorGraduateGone);
        }

        return majorGraduateGoneList;
    }

    @GetMapping("/statistics/graduatesalary")
    public List<MajorSalary> queryMajorSalary(@RequestParam String year){

        List<MajorSalary> majorSalaryList=iMajorManagerService.queryMajorYearSalary(year);
        return majorSalaryList;
    }
    @GetMapping("/statistics/graduateindustry")
    public List<IndustryGraduate> queryIndustryGraduate(@RequestParam String year){
        List<IndustryGraduate> industryGraduateList=iMajorManagerService.queryIndustryGraduate(year);
        return industryGraduateList;
    }

    @GetMapping("/statistics/graduateworkrate")
    public List<MajorRate> queryMajorWorkRate(@RequestParam String year){
        System.out.println("year");
        System.out.println(year);
        List<MajorEntity> majorEntityList = iMajorManagerService.queryAllMajor();
        List<MajorRate> majorRateList = new ArrayList<>();

        for (MajorEntity majorEntity : majorEntityList) {
            String majorId = String.valueOf(majorEntity.getMid());
            System.out.println(majorId);
            List<MajorGraduateGone> majorGraduateGones = iMajorManagerService.queryMajorGraduate(majorId, year);

            System.out.println(majorGraduateGones);

            int totalGraduateCount = 0;
            int typeID2Count = 0;

            for (MajorGraduateGone majorGraduateGone : majorGraduateGones) {
                totalGraduateCount =majorGraduateGone.getGraduateCountID1()+majorGraduateGone.getGraduateCountID3()+majorGraduateGone.getGraduateCountID2();

//                if (majorGraduateGone.getTypeID() == 2) {
//                    typeID2Count += majorGraduateGone.getGraduateCount();
//                }
                typeID2Count=majorGraduateGone.getGraduateCountID2();
            }
            System.out.println(totalGraduateCount);
            System.out.println(typeID2Count);
            if (totalGraduateCount!=0){
                double typeID2Percentage = (double) typeID2Count / totalGraduateCount;
                System.out.println(typeID2Percentage);

                MajorRate majorRate = new MajorRate();
                majorRate.setMajorName(majorEntity.getMajorName());
                majorRate.setWorkrate(typeID2Percentage);

                majorRateList.add(majorRate);

            }

        }

        return majorRateList;

    }

    @GetMapping("/statistics/graduatelocation")
    public List<GraduatePlace> getGraduateplace(@RequestParam String year){
        List<GraduatePlace> graduatePlaceList=iMajorManagerService.queryGraduatePlace(year);
        return graduatePlaceList;
    }

//    @GetMapping("/employmentReport")
//    public List<JobReport> getJobReport(@RequestParam String year){
//        List<JobReport> jobReportList=new ArrayList<>();
//        List<GraduatePlace> graduatePlaceList=getGraduateplace(year);
//        List<MajorGraduateGone> majorGraduateGoneList=queryMajorGraduateGone(year);
//        List<MajorSalary> majorSalaryList=queryMajorSalary(year);
//        List<MajorRate> majorRateList=queryMajorWorkRate(year);
//        List<IndustryGraduate> industryGraduateList=queryIndustryGraduate(year);
//        JobReport jobReport = new JobReport();
//        jobReport.setGraduatePlaceList(graduatePlaceList);
//        jobReport.setMajorGraduateGoneList(majorGraduateGoneList);
//        jobReport.setMajorSalaryList(majorSalaryList);
//        jobReport.setMajorRateList(majorRateList);
//        jobReport.setIndustryGraduateList(industryGraduateList);
//
//        jobReportList.add(jobReport);
//
//        return jobReportList;
//    }
        @GetMapping("/employmentReport")
        public Map<String, Object> getJobReport(@RequestParam String year,@RequestParam int userid) {
            System.out.println("heresuccess");
            Map<String, Object> result = new HashMap<>();
            String uniqueString = UUID.randomUUID().toString();
            result.put("path", uniqueString);
            Map<String, Object> report = new LinkedHashMap<>();
//            String s= "呈现";
//            report.put("根据以下数据生成{{"+year+"年}}的毕业生就业质量报告，要求如下：\n" + "1.语法上采用markdown语法，注意使用各级标题，严格按照论文格式(标题格式：一、1，1、1，2...)，语言正式官方，具有参考价值\n" + "2.结构上分为总（一个段落，内容详实的概述）-分(若干个段落分别呈现和分析下列各数据)-总(一个段落，总结并展望未来，分析得出对学校和学生有参考价值的结论)\n" + "3.数据分析部分必须包含两部分：文字描述(对数据的文字解释和分析)+统计图表(采用markdown的table语法,填入json中的数据)的方式呈现\n",null);
            System.out.println(report);
            List<GraduatePlace> graduatePlaceList = getGraduateplace(year);
            List<MajorGraduateGone> majorGraduateGoneList = queryMajorGraduateGone(year);
            List<MajorSalary> majorSalaryList = queryMajorSalary(year);
            List<MajorRate> majorRateList = queryMajorWorkRate(year);
            List<IndustryGraduate> industryGraduateList = queryIndustryGraduate(year);
            System.out.println("heresuccess");



//            report.put("根据以下数据生成"+year+"年的毕业生就业质量报告\n" +
//                    "要求如下：\n" +
//                    "1.语法上采用markdown语法，注意使用各级标题，严格按照论文格式(标题格式：一、1，1、1，2...)，语言正式官方，具有参考价值\n" +
//                    "2.结构上分为开头上一个段落，内容详实的概述，中间若干个段落分别呈现和分析下列各数据，最后一个段落，总结并展望未来，分析得出对学校和学生有参考价值的结论)\n" +
//                    "3.数据分析部分必须包含两部分：文字描述(对数据的文字解释和分析)+统计图表(采用markdown的table语法,填入json中的数据)的方式呈现" ,"");
//            report.put("1.各专业毕业生平均薪资", majorSalaryList);
//            System.out.println(report);
//            report.put("2.各专业毕业生就业率", majorRateList);
//            report.put("3.毕业生行业分布", industryGraduateList);
//            report.put("4.毕业生地域分布", graduatePlaceList);
//            report.put("5.各专业毕业生去向(其中\"graduateCountID1\"指的是出国," +
//                    "\"graduateCountID2\"指的是就业," +
//                    "\"graduateCountID3\"指的是升学)", majorGraduateGoneList);

            report.put("根据以下数据生成"+year+"年的毕业生就业质量报告\n" +
                    "要求如下：\n" +
                    "1.语法上采用markdown语法，注意使用各级标题，严格按照论文格式(标题格式：一、1，1、1，2...)，语言正式官方，具有参考价值\n" +
                    "2.结构上分为开头上一个段落，内容详实的概述，中间若干个段落分别呈现和分析下列各数据，最后一个段落，总结并展望未来，分析得出对学校和学生有参考价值的结论)\n" +
                    "3.数据分析部分必须包含两部分：文字描述(对数据的文字解释和分析)+统计图表(采用markdown的table语法,填入json中的数据)的方式呈现" ,"");
            report.put("1.各专业毕业生平均薪资", majorSalaryList);
            System.out.println(report);
            report.put("2.各专业毕业生就业率", majorRateList);
            report.put("3.毕业生行业分布", industryGraduateList);
            report.put("4.毕业生地域分布", graduatePlaceList);
            report.put("5.各专业毕业生去向(其中\"graduateCountID1\"指的是出国," +
                    "\"graduateCountID2\"指的是就业," +
                    "\"graduateCountID3\"指的是升学)", majorGraduateGoneList);
            report.put("以下是一个就业质量报告示例，模仿示例生成上面要求的就业质量报告<!doctype html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<meta charset='UTF-8'><meta name='viewport' content='width=device-width initial-scale=1'>\n" +
                    "<title>test</title></head>\n" +
                    "<body><h1>2022年毕业生就业质量报告</h1>\n" +
                    "<h2>概述</h2>\n" +
                    "<p>本报告旨在分析2022年毕业生的就业质量情况，并提供对学校和学生有参考价值的结论。报告将从各专业毕业生平均薪资、就业率、行业分布、地域分布以及各专业毕业生去向等方面进行详实的分析和解释。</p>\n" +
                    "<h2>各专业毕业生平均薪资</h2>\n" +
                    "<p>下表展示了2022年各专业毕业生的平均薪资情况：</p>\n" +
                    "<figure><table>\n" +
                    "<thead>\n" +
                    "<tr><th>专业名称</th><th>平均薪资（万元）</th></tr></thead>\n" +
                    "<tbody><tr><td>社会学</td><td>4.0</td></tr><tr><td>数学与应用数学</td><td>8.0</td></tr><tr><td>应用物理学</td><td>5.0</td></tr></tbody>\n" +
                    "</table></figure>\n" +
                    "<p>从数据可以看出，数学与应用数学专业的毕业生平均薪资最高，为8.0万元，而社会学专业的毕业生平均薪资最低，为4.0万元。</p>\n" +
                    "<h2>各专业毕业生就业率</h2>\n" +
                    "<p>下表展示了2022年各专业毕业生的就业率情况：</p>\n" +
                    "<figure><table>\n" +
                    "<thead>\n" +
                    "<tr><th>专业名称</th><th>就业率</th></tr></thead>\n" +
                    "<tbody><tr><td>社会学</td><td>0.0</td></tr><tr><td>数学与应用数学</td><td>1.0</td></tr><tr><td>应用物理学</td><td>0.9888888889</td></tr></tbody>\n" +
                    "</table></figure>\n" +
                    "<p>数据显示，数学与应用数学专业的毕业生就业率最高，达到了100%，而社会学专业的毕业生就业率为0%。</p>\n" +
                    "<h2>毕业生行业分布</h2>\n" +
                    "<p>下表展示了2022年毕业生的行业分布情况：</p>\n" +
                    "<figure><table>\n" +
                    "<thead>\n" +
                    "<tr><th>行业名称</th><th>人数</th></tr></thead>\n" +
                    "<tbody><tr><td>房地产</td><td>91</td></tr><tr><td>金融</td><td>90</td></tr><tr><td>银行</td><td>1</td></tr><tr><td>游戏</td><td>88</td></tr></tbody>\n" +
                    "</table></figure>\n" +
                    "<p>数据显示，房地产和金融行业是毕业生就业的主要领域，分别有91人和90人就业于此。银行行业只有1人就业，而游戏行业有88人就业。</p>\n" +
                    "<h2>毕业生地域分布</h2>\n" +
                    "<p>下表展示了2022年毕业生的地域分布情况：</p>\n" +
                    "<figure><table>\n" +
                    "<thead>\n" +
                    "<tr><th>地域</th><th>人数</th></tr></thead>\n" +
                    "<tbody><tr><td>石家庄</td><td>91</td></tr><tr><td>唐山</td><td>90</td></tr><tr><td>秦皇岛</td><td>89</td></tr></tbody>\n" +
                    "</table></figure>\n" +
                    "<p>数据显示，石家庄是毕业生就业的主要地区，有91人就业于此。其次是唐山和秦皇岛，分别有90人和89人就业于这两个地区。</p>\n" +
                    "<h2>各专业毕业生去向</h2>\n" +
                    "<p>下表展示了2022年各专业毕业生的去向情况：</p>\n" +
                    "<figure><table>\n" +
                    "<thead>\n" +
                    "<tr><th>专业名称</th><th>出国人数</th><th>就业人数</th><th>升学人数</th></tr></thead>\n" +
                    "<tbody><tr><td>社会学</td><td>1</td><td>0</td><td>90</td></tr><tr><td>数学与应用数学</td><td>0</td><td>89</td><td>0</td></tr><tr><td>应用物理学</td><td>0</td><td>89</td><td>1</td></tr></tbody>\n" +
                    "</table></figure>\n" +
                    "<p>数据显示，社会学专业的毕业生中有1人选择出国，没有人就业，而有90人选择升学。数学与应用数学专业的毕业生中有89人就业，没有人选择出国或升学。应用物理学专业的毕业生中有89人就业，1人选择升学，没有人选择出国。</p>\n" +
                    "<h2>总结与展望</h2>\n" +
                    "<p>综合以上数据分析，可以得出以下结论：</p>\n" +
                    "<ol start='' >\n" +
                    "<li>数学与应用数学专业的毕业生平均薪资最高，为8.0万元，而社会学专业的毕业生平均薪资最低，为4.0万元。</li>\n" +
                    "<li>数学与应用数学专业的毕业生就业率最高，达到了100%，而社会学专业的毕业生就业率为0%。</li>\n" +
                    "<li>房地产和金融行业是毕业生就业的主要领域，而银行行业就业人数较少。</li>\n" +
                    "<li>石家庄是毕业生就业的主要地区，其次是唐山和秦皇岛。</li>\n" +
                    "<li>社会学专业的毕业生更倾向于选择升学，而数学与应用数学和应用物理学专业的毕业生更倾向于选择就业。</li>\n" +
                    "\n" +
                    "</ol>\n" +
                    "<p>未来，学校可以进一步加强社会学专业的就业指导，提高其就业率。同时，学生在选择专业时可以考虑就业前景和薪资水平，以及行业和地域的就业情况，做出更明智的决策。</p>\n" +
                    "<p>以上报告为2022年毕业生就业质量的数据分析和解释，希望对学校和学生有参考价值。</p>\n" +
                    "</body>\n" +
                    "</html>","");


            result.put("prompt", report);
            Report report1=new Report();
            report1.setReportID(0);
            report1.setState(0);

            String time= String.valueOf(new Timestamp(new Date().getTime()));
            System.out.println(time);
            report1.setTime(time);
//            String uniqueString = UUID.randomUUID().toString();
            report1.setUrl(uniqueString);
            report1.setUser_userID(userid);
            report1.setTitle(year+"年就业质量报告");
            Boolean flag=iReportManagerService.addReport(report1);
            System.out.println("try insert");
            System.out.println(flag);

            return result;
        }








}
