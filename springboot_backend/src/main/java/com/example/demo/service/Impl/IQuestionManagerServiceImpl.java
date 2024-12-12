package com.example.demo.service.Impl;

import com.example.demo.dao.IQuestionManagerDao;
import com.example.demo.entities.QuestionSelect;
import com.example.demo.entities.QuestionnaireEntity;
import com.example.demo.service.IQuestionManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IQuestionManagerServiceImpl implements IQuestionManagerService {
    @Autowired
    IQuestionManagerDao iQuestionManagerDao;

    @Override
    public List<QuestionSelect> queryQuestionnaire() {
        List<QuestionSelect> questionnaireEntityList=iQuestionManagerDao.queryQuestionnaire();
        return questionnaireEntityList;
    }

    @Override
    public Boolean addQuestionnaire(QuestionnaireEntity QuestionnaireEntity) {
        Boolean flag=iQuestionManagerDao.addQuestionnaire(QuestionnaireEntity);
        return flag;
    }

    @Override
    public Boolean delQuestionnaire(int id) {
        Boolean flag=iQuestionManagerDao.delQuestionnaire(id);
        return flag;
    }

    @Override
    public Boolean updateQuestionnaire(String state,String id) {
        Boolean flag=iQuestionManagerDao.updateQuestionnaire(state,id);
        return flag;
    }


//    public QuestionnaireEntity likeQuestionnaire(int id) {
//        return null;
//    }
    @Override
    public List<QuestionSelect> queryLikeQuestionnaire(String keyword){
        List<QuestionSelect> questionnaireEntityList=iQuestionManagerDao.queryLikeQuestionnaire(keyword);
        return questionnaireEntityList;
    }
}
