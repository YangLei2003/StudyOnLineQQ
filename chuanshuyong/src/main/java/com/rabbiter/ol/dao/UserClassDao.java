package com.rabbiter.ol.dao;

import com.rabbiter.ol.vo.UserClassVo;
import com.rabbiter.ol.entity.UserClassEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
public interface UserClassDao extends BaseMapper<UserClassEntity> {

    Integer queryCount(UserClassVo userClassVo);

    List<HashMap> queryData(UserClassVo userClassVo);

    List<UserClassEntity> selectByClassId(Integer classId);

    List<HashMap> findList(UserClassVo userClassVo);

    @Delete("delete from user_class where user_id=#{id}")
    void remove(Integer id);

    @Select("select from user_class where class_id=#{classId}")
    List<UserClassEntity> getUserId(Integer classId);
}
