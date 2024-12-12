package com.example.demo.service;

import com.example.demo.entities.DictionaryEntity;

import java.util.List;

public interface IDictionaryManagerService {
    public List<DictionaryEntity> queryDict();



    public Boolean addDict(DictionaryEntity dictionaryEntity);


    public Boolean delDict(int id);

    public Boolean updateDict(DictionaryEntity dictionaryEntity);
}
