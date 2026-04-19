package com.rabbiter.ol.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;


import com.rabbiter.ol.common.Result;
import com.rabbiter.ol.entity.UserDoExerciseEntity;
import com.rabbiter.ol.service.UserDoExerciseService;
import com.rabbiter.ol.vo.UserDoExerciseVo;
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
@RequestMapping("study/userDoExercise")
public class UserDoExerciseController {
    @Autowired
    private UserDoExerciseService userDoExerciseService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestBody UserDoExerciseVo userDoExerciseVo) {
        userDoExerciseVo.setPage((userDoExerciseVo.getPage() - 1) * userDoExerciseVo.getPageSize());
        Map<String, Object> page = userDoExerciseService.queryPage(userDoExerciseVo);
        return Result.success(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Integer id) {
        UserDoExerciseEntity userDoExercise = userDoExerciseService.getById(id);

        return Result.success(userDoExercise);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody UserDoExerciseVo userDoExerciseVo) {
        UserDoExerciseEntity userDoExerciseEntity = new UserDoExerciseEntity();
        userDoExerciseEntity.setExerciseId(userDoExerciseVo.getExerciseId());
        userDoExerciseEntity.setReply(userDoExerciseVo.getContent());
        userDoExerciseEntity.setUserId(userDoExerciseVo.getUserId());
        userDoExerciseEntity.setCreateTime(new Date());
        boolean save = userDoExerciseService.save(userDoExerciseEntity);
        if (save){
            return Result.successCode();
        }
        return Result.failureCode();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody UserDoExerciseEntity userDoExercise) {
        boolean b = userDoExerciseService.updateById(userDoExercise);
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
        userDoExerciseService.removeByIds(Arrays.asList(ids));

        return Result.successCode();
    }

    @RequestMapping("/updateScore/{id}/{score}")
    public Result updateScore(@PathVariable("id") Integer id, @PathVariable("score") Integer score) {
        userDoExerciseService.updateScoreById(id, score);
        return Result.successCode();
    }

}
