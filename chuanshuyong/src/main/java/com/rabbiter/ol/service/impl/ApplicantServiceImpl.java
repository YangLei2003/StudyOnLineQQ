package com.rabbiter.ol.service.impl;

import com.rabbiter.ol.dao.ApplicantDao;
import com.rabbiter.ol.entity.ApplicantEntity;
import com.rabbiter.ol.service.ApplicantService;
import com.rabbiter.ol.vo.ApplicantVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("applicantService")
public class ApplicantServiceImpl extends ServiceImpl<ApplicantDao, ApplicantEntity> implements ApplicantService {

    @Autowired
    private ApplicantDao applicantDao;

    @Override
    public Map<String, Object> queryPage(ApplicantVo applicantVo) {
        Integer total = applicantDao.queryCount(applicantVo);
        List<HashMap> data = applicantDao.queryData(applicantVo);
        Map<String, Object> result = new HashMap<>();
        result.put("total",total);
        result.put("data",data);
        return result;
    }
}