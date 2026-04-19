package com.rabbiter.ol.dao;

import com.rabbiter.ol.entity.SubjectEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rabbiter.ol.vo.SubjectVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.security.auth.Subject;
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
public interface SubjectDao extends BaseMapper<SubjectEntity> {

    Integer queryCount(SubjectVo subjectVo);

    List<HashMap> queryData(SubjectVo subjectVo);

    List<HashMap> queryList(SubjectVo subjectVo);

    Integer findPageCount(SubjectVo subjectVo);

    List<HashMap> findPageData(SubjectVo subjectVo);

    @Select("select * from subject")
    List<SubjectEntity> getAllSubject();
}
