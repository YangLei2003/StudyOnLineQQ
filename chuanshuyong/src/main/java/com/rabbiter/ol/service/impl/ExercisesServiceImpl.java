package com.rabbiter.ol.service.impl;

import com.rabbiter.ol.dao.ExercisesDao;
import com.rabbiter.ol.entity.ExercisesEntity;
import com.rabbiter.ol.service.ExercisesService;
import com.rabbiter.ol.vo.ExercisesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("exercisesService")
public class ExercisesServiceImpl extends ServiceImpl<ExercisesDao, ExercisesEntity> implements ExercisesService {

    @Autowired
    private ExercisesDao exercisesDao;

    @Override
    public Map<String, Object> queryPage(ExercisesVo exercisesVo) {
        Integer total = exercisesDao.queryCount(exercisesVo);
        List<HashMap> data = exercisesDao.queryData(exercisesVo);
        Map<String, Object> result = new HashMap<>();
        result.put("total",total);
        result.put("data",data);
        return result;
    }

    @Override
    public Map<String, Object> findNotDoExercises(ExercisesVo exercisesVo) {
        Integer total = exercisesDao.findNotDoExercisesCount(exercisesVo);
        List<HashMap> data = exercisesDao.findNotDoExercisesData(exercisesVo);
        Map<String, Object> result = new HashMap<>();
        result.put("total",total);
        result.put("data",data);
        return result;
    }
}