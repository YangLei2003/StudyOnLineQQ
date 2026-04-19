package com.rabbiter.ol.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.rabbiter.ol.dao.SubjectDao;
import com.rabbiter.ol.entity.SubjectEntity;
import com.rabbiter.ol.service.SubjectService;
import com.rabbiter.ol.vo.SubjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("subjectService")
public class SubjectServiceImpl extends ServiceImpl<SubjectDao, SubjectEntity> implements SubjectService {

    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Map<String, Object> queryPage(SubjectVo subjectVo) {
        Integer total = subjectDao.queryCount(subjectVo);
        List<HashMap> data = subjectDao.queryData(subjectVo);
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("data", data);
        return result;
    }

    @Override
    public List<HashMap> queryList(SubjectVo subjectVo) {
        List<HashMap> data = subjectDao.queryList(subjectVo);
        return data;
    }


    /**
     *科目查询功能
     * 1.首先拿到键查询Redis中是否缓存的对应的Key
     *  1.如果有，直接返回，不用查询数据库，减轻数据库压力
     *  2.如果没有，查询数据库
     *  引入Redis缓存技术：可以及其快速的返回数据并且减轻数据库压力
     */
    @Override
    public Map<String, Object> findPage(SubjectVo subjectVo) {
        //拿到前端传入的科目ID
        Integer subjectId = subjectVo.getId();
        //拼接Redis的键
        String redisKey = "cache:subject_key：" + subjectId;
        //根据键来查询是否存在数据
        String subjectCache = stringRedisTemplate.opsForValue().get(redisKey);
        //如果存在，直接返回Redis中存的数据
        if (StrUtil.isNotBlank(subjectCache)) {
            Map<String,Object> result=new HashMap<>();
            result.put("total",subjectDao.findPageCount(subjectVo));
            result.put("data",JSONUtil.parseObj(subjectCache).toBean(HashMap.class));
            return result;
        }

        //如果不存在，则去查询数据库
        Integer total = subjectDao.findPageCount(subjectVo);
        List<HashMap> data = subjectDao.findPageData(subjectVo);

        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("data", data);

        return result;

    }
}