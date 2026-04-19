package com.rabbiter.ol.dao;

import com.rabbiter.ol.vo.UserSubjectVo;
import com.rabbiter.ol.entity.UserSubjectEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * 
 * @author 
 * @email ${email}
 * @date 2024-02-15 21:39:15
 */
@Mapper
public interface UserSubjectDao extends BaseMapper<UserSubjectEntity> {

    Integer queryCount(UserSubjectVo userSubjectVo);

    List<HashMap> queryData(UserSubjectVo userSubjectVo);
}
