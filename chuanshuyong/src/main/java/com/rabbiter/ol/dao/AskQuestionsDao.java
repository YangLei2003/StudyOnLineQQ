package com.rabbiter.ol.dao;

import com.rabbiter.ol.vo.AskQuestionsVo;
import com.rabbiter.ol.entity.AskQuestionsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * 
 * @author 
 * @email ${email}
 * @date 2024-02-12 00:24:21
 */
@Mapper
public interface AskQuestionsDao extends BaseMapper<AskQuestionsEntity> {

    Integer queryCount(AskQuestionsVo askQuestionsVo);

    List<HashMap> queryData(AskQuestionsVo askQuestionsVo);
	
}
