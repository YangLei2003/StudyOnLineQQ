package com.rabbiter.ol.service.impl;

import com.rabbiter.ol.dao.UserDoExerciseDao;
import com.rabbiter.ol.entity.UserDoExerciseEntity;
import com.rabbiter.ol.service.UserDoExerciseService;
import com.rabbiter.ol.vo.UserDoExerciseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("userDoExerciseService")
public class UserDoExerciseServiceImpl extends ServiceImpl<UserDoExerciseDao, UserDoExerciseEntity> implements UserDoExerciseService {

    @Autowired
    private UserDoExerciseDao userDoExerciseDao;

    @Override
    public Map<String, Object> queryPage(UserDoExerciseVo userDoExerciseVo) {
        Integer total = userDoExerciseDao.queryCount(userDoExerciseVo);
        List<HashMap> data = userDoExerciseDao.queryData(userDoExerciseVo);
        Map<String, Object> result = new HashMap<>();
        result.put("total",total);
        result.put("data",data);
        return result;
    }

    @Override
    public void updateScoreById(Integer id, Integer score) {
        UserDoExerciseEntity userDoExerciseEntity = userDoExerciseDao.selectById(id);
        if(userDoExerciseEntity == null) {
            return;
        }
        userDoExerciseEntity.setScore(score);
        userDoExerciseDao.updateById(userDoExerciseEntity);
    }
}