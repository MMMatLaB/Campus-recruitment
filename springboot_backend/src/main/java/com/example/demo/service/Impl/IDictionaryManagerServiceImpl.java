package com.example.demo.service.Impl;

import com.example.demo.dao.IDictionaryManagerDao;
import com.example.demo.entities.DictionaryEntity;
import com.example.demo.service.IDictionaryManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IDictionaryManagerServiceImpl implements IDictionaryManagerService {

    @Autowired
    IDictionaryManagerDao iDictionaryManagerDao;

    @Override
    public List<DictionaryEntity> queryDict() {
        List<DictionaryEntity> dictionaryEntityList=iDictionaryManagerDao.queryDict();
        return dictionaryEntityList;

    }

    @Override
    public Boolean addDict(DictionaryEntity dictionaryEntity) {
        Boolean flag=iDictionaryManagerDao.addDict(dictionaryEntity);
        return flag;
    }

    @Override
    public Boolean delDict(int id) {
        Boolean flag=iDictionaryManagerDao.delDict(id);
        return flag;
    }
    @Override
    public Boolean updateDict(DictionaryEntity dictionaryEntity){
        Boolean flag=iDictionaryManagerDao.updateDict(dictionaryEntity);
        return flag;
    }
}
