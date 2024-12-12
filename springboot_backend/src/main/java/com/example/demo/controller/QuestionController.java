package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.entities.QuestionSelect;
import com.example.demo.entities.QuestionnaireEntity;
import com.example.demo.service.IQuestionManagerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class QuestionController {
    @Autowired
    IQuestionManagerService iQuestionManagerService;

    @GetMapping("/questionnaires")
    public List<QuestionSelect> queryQuestionnaireList(@RequestParam(value="title",required = false) String keyword) {

        List<QuestionSelect> QuestionnaireEntityList;
        if (keyword.equals("")){
            QuestionnaireEntityList=iQuestionManagerService.queryQuestionnaire();
        }
        else {
            QuestionnaireEntityList = iQuestionManagerService.queryLikeQuestionnaire(keyword);
        }
        for (QuestionSelect QuestionnaireEntity : QuestionnaireEntityList) {
            System.out.println(QuestionnaireEntity);
        }
        return QuestionnaireEntityList;

    }

    @PostMapping("/questionnaire")
    @ApiOperation("insertQuestionnaire")
    @CrossOrigin
    public R insertQuestionnaire(@RequestBody QuestionnaireEntity QuestionnaireEntity, HttpSession session) {
        System.out.println("here success");
        System.out.println(QuestionnaireEntity);
        // 前端传过来的 Questionnaire 对象 判断用户名和密码是否存在
        // 将Questionnaire插入数据库
        //检验格式是否正确的在前端
        boolean flag = iQuestionManagerService.addQuestionnaire(QuestionnaireEntity);
        System.out.println(flag);
        if (flag) {
            return R.ok("插入成功");
        } else {
            return R.error("插入失败");
        }
    }

    @PutMapping("/questionnaire/{id}")
    @ApiOperation("updateQuestionnaire")
    public R updateQuestionnaire(@PathVariable String id, @RequestParam String status, HttpSession session) {
//        QuestionnaireEntity QuestionnaireEntity = iQuestionManagerService.likeQuestionnaire(id);
        System.out.println("status");
        System.out.println(status);

        Boolean flag = iQuestionManagerService.updateQuestionnaire(status,id);
        if (flag) {
            return R.ok("更新成功");
        } else {
            return R.error("更新失败");
        }
    }

    @DeleteMapping("/questionnaire/{id}")
    @ApiOperation("deleteQuestionnaire")
    public R deleteQuestionnaire(@PathVariable int id, HttpSession session) {
        Boolean flag = iQuestionManagerService.delQuestionnaire(id);
        if (flag) {
            return R.ok("删除成功");
        } else {
            return R.error("删除失败");
        }
    }
}
