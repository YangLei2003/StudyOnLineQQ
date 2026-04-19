package com.rabbiter.ol.dao;

import com.rabbiter.ol.vo.ApplicantVo;
import com.rabbiter.ol.entity.ApplicantEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * 
 * @author 
 * @email ${email}
 * @date 2024-02-13 00:22:11
 */
@Mapper
public interface ApplicantDao extends BaseMapper<ApplicantEntity> {

    Integer queryCount(ApplicantVo applicantVo);

    List<HashMap> queryData(ApplicantVo applicantVo);
}
