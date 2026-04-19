package com.rabbiter.ol.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rabbiter.ol.common.Result;
import com.rabbiter.ol.entity.SubjectEntity;
import com.rabbiter.ol.service.SubjectService;
import com.rabbiter.ol.vo.SubjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 
 *
 * @author 
 * @email ${email}
 * @date 2024-02-15 21:39:15
 */
@RestController
@RequestMapping("study/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestBody SubjectVo subjectVo){
        subjectVo.setPage((subjectVo.getPage() - 1) * subjectVo.getPageSize());
        Map<String, Object> page = subjectService.queryPage(subjectVo);
        return Result.success(page);
    }

    /**
     * 列表
     */
    @RequestMapping("/findPage")
    public Result findPage(@RequestBody SubjectVo subjectVo){
        subjectVo.setPage((subjectVo.getPage() - 1) * subjectVo.getPageSize());
        Map<String, Object> page = subjectService.findPage(subjectVo);
        return Result.success(page);
    }

    /**
     * 列表
     */
    @RequestMapping("/queryList")
    public Result queryList(@RequestBody SubjectVo SubjectVo) {
        List<HashMap> page = subjectService.queryList(SubjectVo);
        return Result.success(page);
    }

    /**
     * 列表
     */
    @GetMapping("/findList")
    public Result findList() {
        List<SubjectEntity> list = subjectService.list();
        return Result.success(list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Integer id){
		SubjectEntity subject = subjectService.getById(id);
        return Result.success(subject);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody SubjectEntity subject){
        boolean save = subjectService.save(subject);
        if (save) {
            return Result.successCode();
        }
        return Result.failureCode();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody SubjectEntity subject){
        boolean update = subjectService.updateById(subject);
        if (update) {

            return Result.successCode();
        }
        return Result.failureCode();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody SubjectEntity subjectEntity){
        boolean b = subjectService.removeById(subjectEntity.getId());
        if (b) {
            return Result.successCode();
        }
        return Result.failureCode();
    }

}
