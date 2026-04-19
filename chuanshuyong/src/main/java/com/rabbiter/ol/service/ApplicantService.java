package com.rabbiter.ol.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbiter.ol.entity.ApplicantEntity;
import com.rabbiter.ol.vo.ApplicantVo;

import java.util.Map;

/**
 * 
 *
 * @author 
 * @email ${email}
 * @date 2024-02-13 00:22:11
 */
public interface ApplicantService extends IService<ApplicantEntity> {

    Map<String, Object> queryPage(ApplicantVo applicantVo);
}

