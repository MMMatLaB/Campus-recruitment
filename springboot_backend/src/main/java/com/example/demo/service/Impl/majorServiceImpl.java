package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.dao.MajorDao;
import com.example.demo.entities.MajorEntity;
import com.example.demo.service.majorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class majorServiceImpl implements majorService {
    @Autowired
    MajorDao majorDao;

    @Override
    public List<MajorEntity> queryByMajorName(String majorName) {
        QueryWrapper<MajorEntity> qwMajor=new QueryWrapper<>();
        if (majorName.equals("")){
            return null;
        }
        qwMajor.like("majorName",majorName);
        //qwMajor.select("mid");
        //List<Integer> midList= majorDao.selectList(qwMajor);
        List<MajorEntity> majorEntityList=majorDao.selectList(qwMajor);
        List<Map<String,Object>> midList=majorDao.selectMaps(qwMajor);
        return majorEntityList;
    }

    @Override
    public String queryName(int mid) {
        QueryWrapper<MajorEntity> qwMajor = new QueryWrapper<>();
        qwMajor.eq("mid", mid); // 使用eq来匹配专业id
        qwMajor.select("majorName");

        MajorEntity majorEntity = majorDao.selectOne(qwMajor);

        // 检查是否找到了匹配的专业记录
        if (majorEntity != null) {
            return majorEntity.getMajorName();
        } else {
            // 如果未找到匹配的专业记录，可以根据实际需求返回一个默认值或者抛出异常等处理
            return "未知专业";
        }
    }

    @Override
    public MajorEntity queryByName(String name) {
        QueryWrapper<MajorEntity> qwMajor = new QueryWrapper<>();
        qwMajor.eq("majorName", name); // 使用eq来匹配专业id
        MajorEntity majorEntity = majorDao.selectOne(qwMajor);
        return majorEntity;
    }

}
