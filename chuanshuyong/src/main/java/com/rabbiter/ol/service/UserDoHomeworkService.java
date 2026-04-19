package com.rabbiter.ol.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.rabbiter.ol.entity.UserDoHomeworkEntity;
import com.rabbiter.ol.vo.UserDoHomeworkVo;

import java.util.Map;

/**
 * 
 *
 * @author 
 * @email ${email}
 * @date 2024-02-12 00:24:20
 */
public interface UserDoHomeworkService extends IService<UserDoHomeworkEntity> {


    Map<String, Object> queryPage(UserDoHomeworkVo userDoHomeworkVo);

    Boolean updateModeByUserId(String userId, String homeworkId,String score,String remark);

}

