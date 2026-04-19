package com.rabbiter.ol.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.ol.dao.ClassDao;
import com.rabbiter.ol.dao.UserClassDao;
import com.rabbiter.ol.dao.VideoTotalDao;
import com.rabbiter.ol.entity.ClassEntity;
import com.rabbiter.ol.entity.VideoTotalEntity;
import com.rabbiter.ol.service.VideoTotalService;
import com.rabbiter.ol.vo.VideoTotalVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("videoTotalService")
public class VideoTotalServiceImpl extends ServiceImpl<VideoTotalDao, VideoTotalEntity> implements VideoTotalService {
    @Autowired
    private VideoTotalDao videoTotalDao;
    @Autowired
    private ClassDao classDao;
    @Autowired
    private UserClassDao userClassDao;

    @Override
    public Map<String, Object> queryPage(VideoTotalVo videoTotalVo) {
        Integer total = videoTotalDao.queryCount(videoTotalVo);
        List<HashMap> data = videoTotalDao.queryData(videoTotalVo);
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("data", data);
        return result;
    }

    @Override
    public Page<VideoTotalEntity> pageVideoTotal(VideoTotalVo videoTotalVo) {
        Page<VideoTotalEntity> page = new Page<>(videoTotalVo.getPage(), videoTotalVo.getPageSize());
        QueryWrapper<VideoTotalEntity> videoTotalEntityQueryWrapper = new QueryWrapper<>();
        if (videoTotalVo.getUserId() != null) {
            videoTotalEntityQueryWrapper.eq("user_id", videoTotalVo.getUserId());
        }
        if (videoTotalVo.getClassId() != null) {
            ClassEntity classEntity = classDao.selectById(videoTotalVo.getClassId());
            if (classEntity != null) {

                videoTotalEntityQueryWrapper.eq("user_id", classEntity.getUserId());
            }
        }
        Page<VideoTotalEntity> resultPage = videoTotalDao.selectPage(page, videoTotalEntityQueryWrapper);
        return resultPage;
    }
}