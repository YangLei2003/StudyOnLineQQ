package com.rabbiter.ol.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.rabbiter.ol.entity.UserDoExerciseEntity;
import com.rabbiter.ol.vo.UserDoExerciseVo;

import java.util.Map;

/**
 * 
 *
 * @author 
 * @email ${email}
 * @date 2024-02-12 00:24:20
 */
public interface UserDoExerciseService extends IService<UserDoExerciseEntity> {


    Map<String, Object> queryPage(UserDoExerciseVo userDoExerciseVo);

    void updateScoreById(Integer id, Integer score);
}

