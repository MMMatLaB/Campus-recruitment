package com.example.demo.dao;

import com.example.demo.entities.DictionaryEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IDictionaryManagerDao {
    public List<DictionaryEntity> queryDict();



    public Boolean addDict(DictionaryEntity dictionaryEntity);


    public Boolean delDict(int id);

    public Boolean updateDict(DictionaryEntity dictionaryEntity);
}
