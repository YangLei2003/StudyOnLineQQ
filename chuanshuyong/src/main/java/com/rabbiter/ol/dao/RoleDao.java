package com.rabbiter.ol.dao;

import com.rabbiter.ol.entity.RoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rabbiter.ol.vo.RoleVo;
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
public interface RoleDao extends BaseMapper<RoleEntity> {

    Integer queryCount(RoleVo roleVo);

    List<HashMap> queryData(RoleVo roleVo);
}
