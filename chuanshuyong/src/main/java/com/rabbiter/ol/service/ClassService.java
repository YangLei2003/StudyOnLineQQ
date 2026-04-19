package com.rabbiter.ol.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.rabbiter.ol.entity.ClassEntity;
import com.rabbiter.ol.vo.ClassVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author 
 * @email ${email}
 * @date 2024-02-12 00:22:45
 */
public interface ClassService extends IService<ClassEntity> {

    Map<String, Object> queryPage(ClassVo classVo);

    List<HashMap> findList(ClassVo classVo);
}

