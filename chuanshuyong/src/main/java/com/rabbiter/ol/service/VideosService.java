package com.rabbiter.ol.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.rabbiter.ol.entity.VideosEntity;
import com.rabbiter.ol.vo.VideosVo;

import java.util.Map;

/**
 * 
 *
 * @author 
 * @email ${email}
 * @date 2024-02-12 00:24:20
 */
public interface VideosService extends IService<VideosEntity> {


    Map<String, Object> queryPage(VideosVo videosVo);
}

