package com.rabbiter.ol.dao;

import com.rabbiter.ol.entity.HomeworkEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rabbiter.ol.vo.HomeworkVo;
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
public interface HomeworkDao extends BaseMapper<HomeworkEntity> {

    Integer queryCount(HomeworkVo homeworkVo);

    List<HashMap> queryData(HomeworkVo homeworkVo);

    List<HashMap> findNotDoHomeworkData(HomeworkVo homeworkVo);

    Integer findNotDoHomeworkCount(HomeworkVo homeworkVo);
}
