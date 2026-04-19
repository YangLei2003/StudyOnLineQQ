package com.rabbiter.ol.dao;

import com.rabbiter.ol.vo.UserDoExerciseVo;
import com.rabbiter.ol.entity.UserDoExerciseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * 
 * @author 
 * @email ${email}
 * @date 2024-02-12 00:24:20
 */
@Mapper
public interface UserDoExerciseDao extends BaseMapper<UserDoExerciseEntity> {

    Integer queryCount(UserDoExerciseVo userDoExerciseVo);

    List<HashMap> queryData(UserDoExerciseVo userDoExerciseVo);
}
