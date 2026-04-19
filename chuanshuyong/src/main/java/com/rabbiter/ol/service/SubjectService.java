package com.rabbiter.ol.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbiter.ol.entity.SubjectEntity;
import com.rabbiter.ol.vo.SubjectVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author 
 * @email ${email}
 * @date 2024-02-15 21:39:15
 */
public interface SubjectService extends IService<SubjectEntity> {

    Map<String, Object> queryPage(SubjectVo subjectVo);

    List<HashMap> queryList(SubjectVo subjectVo);

    Map<String, Object> findPage(SubjectVo subjectVo);
}

