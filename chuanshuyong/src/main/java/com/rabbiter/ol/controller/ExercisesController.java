package com.rabbiter.ol.controller;

import java.util.Date;
import java.util.Map;


import com.rabbiter.ol.common.Result;
import com.rabbiter.ol.entity.ExercisesEntity;
import com.rabbiter.ol.service.ExercisesService;
import com.rabbiter.ol.vo.ExercisesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 *
 * @author 
 * @email ${email}
 * @date 2024-02-12 00:24:20
 */
@RestController
@RequestMapping("study/exercises")
public class ExercisesController {
    @Autowired
    private ExercisesService exercisesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestBody ExercisesVo exercisesVo){
        exercisesVo.setPage((exercisesVo.getPage() - 1) * exercisesVo.getPageSize());
        Map<String, Object> page = exercisesService.queryPage(exercisesVo);
        return Result.success(page);
    }

    /**
     * 列表
     */
    @RequestMapping("/findNotDoExercises")
    public Result findNotDoExercises(@RequestBody  ExercisesVo exercisesVo){
        exercisesVo.setPage((exercisesVo.getPage() - 1) * exercisesVo.getPageSize());
        Map<String, Object> page = exercisesService.findNotDoExercises(exercisesVo);
        return Result.success(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Integer id){
		ExercisesEntity exercises = exercisesService.getById(id);

        return Result.success(exercises);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody ExercisesEntity exercises){
        exercises.setCreateTime(new Date());
        boolean save = exercisesService.save(exercises);
        if (save){
            return Result.successCode();
        }
        return Result.failureCode();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody ExercisesEntity exercises){
        boolean b = exercisesService.updateById(exercises);
        if (b){
            return Result.successCode();
        }
        return Result.failureCode();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody ExercisesEntity exercises){
        boolean remove = exercisesService.removeById(exercises.getId());
        if (remove){
            return  Result.successCode();
        }
        return  Result.failureCode();
    }

}
