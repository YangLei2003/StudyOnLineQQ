package com.rabbiter.ol.dao;

import com.rabbiter.ol.vo.LoginVo;
import com.rabbiter.ol.vo.UserVo;
import com.rabbiter.ol.entity.UserEntity;
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
public interface UserDao extends BaseMapper<UserEntity> {

    Integer queryCount(UserVo userVo);

    List<HashMap> queryData(UserVo userVo);

    List<HashMap> login(LoginVo loginVo);

    Integer findNotDoWorkCount(UserVo userVo);

    List<HashMap> findNotDoWorkData(UserVo userVo);

    Integer findNotDoHomeworkCount(UserVo userVo);

    List<HashMap> findNotDoHomeworkData(UserVo userVo);

    List<HashMap> findList(UserVo userVo);
}
