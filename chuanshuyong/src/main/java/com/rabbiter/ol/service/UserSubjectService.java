package com.rabbiter.ol.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbiter.ol.entity.UserSubjectEntity;
import com.rabbiter.ol.vo.UserSubjectVo;

import java.util.Map;

/**
 * 
 *
 * @author 
 * @email ${email}
 * @date 2024-02-15 21:39:15
 */
public interface UserSubjectService extends IService<UserSubjectEntity> {

    Map<String, Object> queryPage(UserSubjectVo userSubjectVo);
}

