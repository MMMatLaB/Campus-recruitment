package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.entities.graduateEntity;
import com.example.demo.entities.whereEntity;
import com.example.demo.service.*;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
@Slf4j
@Api(tags = {"毕业生信息"})//api中的名称
public class graduateController {
    @Autowired
    graduateService graduateService;
    @Autowired
    majorService majorService;
    @Autowired
    locationService locationService;
    @Autowired
    DesTypeService desTypeService;

    @Autowired
    whereService whereService;
    /*@PostMapping("/upload/graduateInfo")
    public R batchImport(@RequestParam("graduateInfo") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return R.error("上传文件为空");
        }

        try {
            byte[] bytes = file.getBytes();
            ImportParams params = new ImportParams();
            params.setHeadRows(1);
            List<graduateEntity> list = ExcelImportUtil.importExcel(new ByteArrayInputStream(bytes), graduateEntity.class, params);
            list.forEach(System.out::println);
            return R.ok("批量上传成功");
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception appropriately
            return R.error("批量上传失败");
        }
    }*/
    // 提取标题中的数字年份的方法
    private String extractYearFromTitle(String title) {
        // 使用正则表达式匹配数字年份
        Pattern pattern = Pattern.compile("\\d{4}");
        Matcher matcher = pattern.matcher(title);
        // 如果找到匹配的年份，返回第一个匹配的结果
        if (matcher.find()) {
            return matcher.group();
        } else {
            // 如果未找到匹配的年份，可以根据实际需求进行处理，例如返回默认年份或抛出异常
            return "默认年份";
        }
    }
    @PostMapping("/upload/graduateInfo")
    public R batchImport(@RequestParam("graduateInfo") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return R.error("上传文件为空");
        }
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // 假设你只处理第一个Sheet
            String title=file.getOriginalFilename();//请实现读取出标题。已知标题是“2024-就业质量报告”类型，再读取出标题后读取出标题中的数字年份。
            // 使用正则表达式提取标题中的数字年份
            String year = extractYearFromTitle(title);
            // 从第二行开始读取数据，跳过表头
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    String uniqueId=getCellValue(row.getCell(0));
                    String name=getCellValue(row.getCell(1));
                    String Gender=getCellValue(row.getCell(2));
                    String company=getCellValue(row.getCell(7));
                    String industry=getCellValue(row.getCell(8));
                    String studySituation=getCellValue(row.getCell(5));
                    //where：找地点 找去向类型,插入where
                    int locationId=locationService.queryByCity(getCellValue(row.getCell(6))).getLocationID();
                    int typeId=desTypeService.queryByName(getCellValue(row.getCell(4))).getTypeId();
                    whereEntity where=new whereEntity(locationId,typeId,company,industry,studySituation);
                    int result=whereService.insert(where);
                    if (result==0){
                        return R.error("插入where失败");
                    }
                    //插入graduate
                    int wid=where.getWid();
                    int mid=majorService.queryByName(getCellValue(row.getCell(3))).getMid();
                    graduateEntity graduate=new graduateEntity(uniqueId,name,year,Gender,mid,wid);
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+year);
                    int result1=graduateService.insert(graduate);
                    if (result1==0){
                        return R.error("插入graduate失败");
                    }

                }
            }
            //list.forEach(System.out::println);
            return R.ok("批量上传成功");

        } catch (Exception e) {
            e.printStackTrace(); // Handle exception appropriately
            return R.error("批量上传失败");
        }
    }

    // 获取单元格的值，根据需要进行类型转换
    private String getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        cell.setCellType(CellType.STRING); // 将单元格数据都转换为字符串处理，根据需要调整
        return cell.getStringCellValue();
    }

}


