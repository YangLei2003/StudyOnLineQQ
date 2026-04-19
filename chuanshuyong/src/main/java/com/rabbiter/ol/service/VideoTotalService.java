package com.rabbiter.ol.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.rabbiter.ol.entity.VideoTotalEntity;
import com.rabbiter.ol.vo.VideoTotalVo;

import java.util.Map;

/**
 * @author 
 * @email ${email}
 * @date 2024-02-12 00:24:20
 */
public interface VideoTotalService extends IService<VideoTotalEntity> {

    Map<String, Object> queryPage(VideoTotalVo videoTotalVo);
    Page<VideoTotalEntity> pageVideoTotal(VideoTotalVo videoTotalVo);

}

