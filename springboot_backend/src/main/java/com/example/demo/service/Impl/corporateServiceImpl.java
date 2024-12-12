package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.dao.corporateDao;
import com.example.demo.entities.MajorEntity;
import com.example.demo.entities.corporateEntity;
import com.example.demo.service.corporateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class corporateServiceImpl implements corporateService {
    @Autowired
    private corporateDao corporateDao;

    @Override
    public List<corporateEntity> queryByCName(String CName) {
        QueryWrapper<corporateEntity> qwMajor=new QueryWrapper<>();
        qwMajor.like("CorpName",CName);
        //List<Integer> midList= majorDao.selectList(qwMajor);
        List<corporateEntity> cidList=corporateDao.selectList(qwMajor);
        return cidList;
    }

    @Override
    public String queryName(int cid) {
        QueryWrapper<corporateEntity> qw = new QueryWrapper<>();
        qw.eq("idcorporation", cid); // 使用eq来匹配专业id
        qw.select("CorpName");
        corporateEntity corporateEntity = corporateDao.selectOne(qw);
        // 检查是否找到了匹配的专业记录
        if (corporateEntity != null) {
            return corporateEntity.getCorpName();
        } else {
            // 如果未找到匹配的专业记录，可以根据实际需求返回一个默认值或者抛出异常等处理
            return "未知公司";
        }
    }

    @Override
    public List<corporateEntity> getAll() {
        List<corporateEntity> list=corporateDao.selectList(null);
        return list;
    }
}
