package com.rabbiter.ol.dao;

import com.rabbiter.ol.vo.VideoTotalVo;
import com.rabbiter.ol.entity.VideoTotalEntity;
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
public interface VideoTotalDao extends BaseMapper<VideoTotalEntity> {

    Integer queryCount(VideoTotalVo videoTotalVo);

    List<HashMap> queryData(VideoTotalVo videoTotalVo);

}
