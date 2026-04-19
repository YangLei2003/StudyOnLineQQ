package com.rabbiter.ol.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.rabbiter.ol.entity.AskQuestionsEntity;
import com.rabbiter.ol.vo.AskQuestionsVo;

import java.util.Map;

/**
 * 
 *
 * @author 
 * @email ${email}
 * @date 2024-02-12 00:24:21
 */
public interface AskQuestionsService extends IService<AskQuestionsEntity> {

    Map<String, Object> queryPage(AskQuestionsVo askQuestionsVo);
}

