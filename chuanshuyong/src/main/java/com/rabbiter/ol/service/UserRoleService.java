package com.rabbiter.ol.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.rabbiter.ol.entity.UserRoleEntity;
import com.rabbiter.ol.vo.UserRoleVo;

import java.util.Map;

/**
 * 
 *
 * @author 
 * @email ${email}
 * @date 2024-02-12 00:22:45
 */
public interface UserRoleService extends IService<UserRoleEntity> {


    Map<String, Object> queryPage(UserRoleVo userRoleVo);
}

