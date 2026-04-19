package com.rabbiter.ol.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.rabbiter.ol.entity.HomeworkEntity;
import com.rabbiter.ol.vo.HomeworkVo;

import java.util.Map;

/**
 * 
 *
 * @author 
 * @email ${email}
 * @date 2024-02-12 00:24:20
 */
public interface HomeworkService extends IService<HomeworkEntity> {


    Map<String, Object> queryPage(HomeworkVo homeworkVo);

    Map<String, Object> findNotDoHomework(HomeworkVo homeworkVo);

}

