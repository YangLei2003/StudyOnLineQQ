package com.rabbiter.ol.dao;

import com.rabbiter.ol.vo.KnowledgePointVo;
import com.rabbiter.ol.entity.KnowledgePointEntity;
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
public interface KnowledgePointDao extends BaseMapper<KnowledgePointEntity> {

    Integer queryCount(KnowledgePointVo knowledgePointVo);

    List<HashMap> queryData(KnowledgePointVo knowledgePointVo);
}
