package com.rabbiter.ol.dao;

import com.rabbiter.ol.vo.VideosVo;
import com.rabbiter.ol.entity.VideosEntity;
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
public interface VideosDao extends BaseMapper<VideosEntity> {

    Integer queryCount(VideosVo videosVo);

    List<HashMap> queryData(VideosVo videosVo);
}
