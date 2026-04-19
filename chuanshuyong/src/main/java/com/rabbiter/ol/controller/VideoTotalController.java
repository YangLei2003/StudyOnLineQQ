package com.rabbiter.ol.controller;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Map;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rabbiter.ol.common.Result;
import com.rabbiter.ol.entity.VideoTotalEntity;
import com.rabbiter.ol.entity.VideosEntity;
import com.rabbiter.ol.service.VideoTotalService;
import com.rabbiter.ol.service.VideosService;
import com.rabbiter.ol.tool.FileUtil;
import com.rabbiter.ol.tool.PathUtils;
import com.rabbiter.ol.vo.VideoTotalVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;


/**
 * @author 
 * @email ${email}
 * @date 2024-02-12 00:24:20
 */
@RestController
@RequestMapping("study/videoTotal")
public class VideoTotalController {

    @Autowired
    private VideoTotalService videoTotalService;

    @Autowired
    private VideosService videosService;

    /**
     * 分页查询
     */
    @RequestMapping("/list")
    public Result list(@RequestBody VideoTotalVo videoTotalVo) {

        videoTotalVo.setPage((videoTotalVo.getPage() - 1) * videoTotalVo.getPageSize());
        Map<String, Object> page = videoTotalService.queryPage(videoTotalVo);
        return Result.success(page);
    }


    @RequestMapping("/pageVideo")
    public Result pageVideo(@RequestBody VideoTotalVo videoTotalVo) {

        Page<VideoTotalEntity> result = videoTotalService.pageVideoTotal(videoTotalVo);
        return Result.success(result);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Integer id) {
        VideoTotalEntity videoTotal = videoTotalService.getById(id);
        return Result.success(videoTotal);
    }

    /**
     * 新增视频集
     */
    @CrossOrigin
    @RequestMapping("/save")
    public Result save(@RequestParam Integer userId,
                       @RequestParam String topic,
                       MultipartFile file) throws UnknownHostException {
        VideoTotalEntity videoTotalEntity = new VideoTotalEntity();
        videoTotalEntity.setTopic(topic);
        videoTotalEntity.setCreateTime(new Date());
        videoTotalEntity.setUserId(userId);
        long randomNum = System.currentTimeMillis();
        videoTotalEntity.setPath(PathUtils.getClassLoadRootPath() + "/file/imageFile/" + randomNum + file.getOriginalFilename());

        videoTotalEntity.setCoverUrl("/file/imageFile/" + randomNum + file.getOriginalFilename());

        boolean save = videoTotalService.save(videoTotalEntity);
        if (save) {
            try {
                FileUtil.uploadFile(file.getBytes(), PathUtils.getClassLoadRootPath() + "/file/imageFile/", randomNum + file.getOriginalFilename());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Result.successCode();
        }
        return Result.failureCode();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody VideoTotalEntity videoTotal) {
        boolean b = videoTotalService.updateById(videoTotal);
        if (b) {
            return Result.successCode();
        }
        return Result.failureCode();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody VideoTotalVo videoTotalVo) {
//        VideoTotalEntity byId = videoTotalService.getById(videoTotalVo.getId());
//        boolean b1 = FileUtil.deleteFile(byId.getPath());
//        if (b1) {
        boolean remove = videoTotalService.removeById(videoTotalVo.getId());
        if (remove) {
            QueryWrapper<VideosEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("video_total_id", videoTotalVo.getId());
            List<VideosEntity> list = videosService.list(queryWrapper);
            for (VideosEntity videosEntity : list) {
                boolean b = FileUtil.deleteFile(videosEntity.getPath());
                if (b) {
                    videosService.remove(queryWrapper);
                }
            }
        }
//        }
        return Result.successCode();
    }


}
