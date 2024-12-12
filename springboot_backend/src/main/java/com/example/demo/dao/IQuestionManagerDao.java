package com.example.demo.dao;

import com.example.demo.entities.QuestionSelect;
import com.example.demo.entities.QuestionnaireEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IQuestionManagerDao {
    public List<QuestionSelect> queryQuestionnaire();



    public Boolean addQuestionnaire(QuestionnaireEntity QuestionnaireEntity);


    public Boolean delQuestionnaire(int id);


    public Boolean updateQuestionnaire(String state,String id);


    public List<QuestionSelect> queryLikeQuestionnaire(String keyword);
}
