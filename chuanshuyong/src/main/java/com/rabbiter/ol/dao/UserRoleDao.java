package com.rabbiter.ol.dao;

import com.rabbiter.ol.vo.UserRoleVo;
import com.rabbiter.ol.entity.UserRoleEntity;
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
public interface UserRoleDao extends BaseMapper<UserRoleEntity> {

    Integer queryCount(UserRoleVo userRoleVo);

    List<HashMap> queryData(UserRoleVo userRoleVo);
}
