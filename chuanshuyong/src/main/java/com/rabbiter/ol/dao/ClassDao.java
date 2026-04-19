package com.rabbiter.ol.dao;

import com.rabbiter.ol.vo.ClassVo;
import com.rabbiter.ol.entity.ClassEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * 
 * @author 
 * @email ${email}
 * @date 2024-02-12 00:22:45
 */
@Mapper
public interface ClassDao extends BaseMapper<ClassEntity> {

    Integer queryCount(ClassVo classVo);

    List<HashMap> queryData(ClassVo classVo);

    List<HashMap> findList(ClassVo classVo);
}
