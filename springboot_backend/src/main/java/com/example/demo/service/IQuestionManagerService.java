package com.example.demo.service;

import com.example.demo.entities.QuestionSelect;
import com.example.demo.entities.QuestionnaireEntity;

import java.util.List;

public interface IQuestionManagerService {
    public List<QuestionSelect> queryQuestionnaire();



    public Boolean addQuestionnaire(QuestionnaireEntity QuestionnaireEntity);


    public Boolean delQuestionnaire(int id);


    public Boolean updateQuestionnaire(String state,String id);


//    public QuestionnaireEntity likeQuestionnaire(int id);
    public List<QuestionSelect> queryLikeQuestionnaire(String keyword);
}
