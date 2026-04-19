package com.rabbiter.ol.service.impl;

import com.rabbiter.ol.dao.ClassDao;
import com.rabbiter.ol.entity.ClassEntity;
import com.rabbiter.ol.service.ClassService;
import com.rabbiter.ol.vo.ClassVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("classService")
public class ClassServiceImpl extends ServiceImpl<ClassDao, ClassEntity> implements ClassService {

    @Autowired
    private ClassDao classDao;

    @Override
    public Map<String, Object> queryPage(ClassVo classVo) {
        Integer total = classDao.queryCount(classVo);
        List<HashMap> data = classDao.queryData(classVo);
        Map<String, Object> result = new HashMap<>();
        result.put("total",total);
        result.put("data",data);
        return result;
    }

    @Override
    public List<HashMap> findList(ClassVo classVo) {
        return classDao.findList(classVo);
    }
}