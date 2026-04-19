package com.rabbiter.ol.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;


import com.rabbiter.ol.common.Result;
import com.rabbiter.ol.entity.UserDoHomeworkEntity;
import com.rabbiter.ol.service.UserDoHomeworkService;
import com.rabbiter.ol.vo.UserDoHomeworkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 
 * @email ${email}
 * @date 2024-02-12 00:24:20
 */
@RestController
@RequestMapping("study/userdohomework")
public class UserDoHomeworkController {
    @Autowired
    private UserDoHomeworkService userDoHomeworkService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestBody UserDoHomeworkVo userDoHomeworkVo) {
        userDoHomeworkVo.setPage((userDoHomeworkVo.getPage() - 1) * userDoHomeworkVo.getPageSize());
        Map<String, Object> page = userDoHomeworkService.queryPage(userDoHomeworkVo);
        return Result.success(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Integer id) {
        UserDoHomeworkEntity userDoHomework = userDoHomeworkService.getById(id);

        return Result.success(userDoHomework);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody UserDoHomeworkVo userDoHomeworkVo) {
        UserDoHomeworkEntity userDoHomeworkEntity = new UserDoHomeworkEntity();
        userDoHomeworkEntity.setHomeworkId(userDoHomeworkVo.getHomeworkId());
        userDoHomeworkEntity.setReply(userDoHomeworkVo.getContent());
        userDoHomeworkEntity.setUserId(userDoHomeworkVo.getUserId());
        userDoHomeworkEntity.setMode("0");
        userDoHomeworkEntity.setCompletionTime(new Date());
        boolean save = userDoHomeworkService.save(userDoHomeworkEntity);
        if (save){
            return Result.successCode();
        }
        return Result.failureCode();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody UserDoHomeworkEntity userDoHomework) {
        boolean b = userDoHomeworkService.updateById(userDoHomework);
        if (b){
            return Result.successCode();
        }
        return Result.failureCode();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Integer[] ids) {
        userDoHomeworkService.removeByIds(Arrays.asList(ids));

        return Result.successCode();
    }

}
