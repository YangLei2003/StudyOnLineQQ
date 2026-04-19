package com.rabbiter.ol.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.rabbiter.ol.entity.ExercisesEntity;
import com.rabbiter.ol.vo.ExercisesVo;

import java.util.Map;

/**
 * 
 *
 * @author 
 * @email ${email}
 * @date 2024-02-12 00:24:20
 */
public interface ExercisesService extends IService<ExercisesEntity> {


    Map<String, Object> queryPage(ExercisesVo exercisesVo);

    Map<String, Object> findNotDoExercises(ExercisesVo exercisesVo);
}

