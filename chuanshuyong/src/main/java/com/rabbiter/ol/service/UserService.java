package com.rabbiter.ol.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.rabbiter.ol.entity.UserEntity;
import com.rabbiter.ol.vo.LoginVo;
import com.rabbiter.ol.vo.UserVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author 
 * @email ${email}
 * @date 2024-02-12 00:24:20
 */
public interface UserService extends IService<UserEntity> {


    Map<String, Object> queryPage(UserVo userVo);

    List<HashMap> login(LoginVo loginVo);

    Map<String, Object> findNotDoWork(UserVo userVo);

    Map<String, Object> findNotDoHomework(UserVo userVo);

    List<HashMap> findList(UserVo userVo);

}

