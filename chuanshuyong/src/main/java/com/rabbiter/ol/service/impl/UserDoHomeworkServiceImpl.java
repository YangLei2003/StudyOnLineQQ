package com.rabbiter.ol.service.impl;

import com.rabbiter.ol.dao.UserDoHomeworkDao;
import com.rabbiter.ol.entity.UserDoHomeworkEntity;
import com.rabbiter.ol.vo.UserDoHomeworkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.rabbiter.ol.service.UserDoHomeworkService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("userDoHomeworkService")
public class UserDoHomeworkServiceImpl extends ServiceImpl<UserDoHomeworkDao, UserDoHomeworkEntity> implements UserDoHomeworkService {

    @Autowired
    private UserDoHomeworkDao userDoHomeworkDao;

    @Override
    public Map<String, Object> queryPage(UserDoHomeworkVo userDoHomeworkVo) {
        Integer total = userDoHomeworkDao.queryCount(userDoHomeworkVo);
        List<HashMap> data = userDoHomeworkDao.queryData(userDoHomeworkVo);
        Map<String, Object> result = new HashMap<>();
        result.put("total",total);
        result.put("data",data);
        return result;
    }



    @Override
    public Boolean updateModeByUserId(String userId, String homeworkId,String score,String remark) {
        return userDoHomeworkDao.updateModeByUserId(userId,homeworkId,score,remark);
    }
}