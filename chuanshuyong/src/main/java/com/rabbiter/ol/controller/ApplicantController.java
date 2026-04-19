package com.rabbiter.ol.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rabbiter.ol.common.Result;
import com.rabbiter.ol.entity.ApplicantEntity;
import com.rabbiter.ol.entity.UserClassEntity;
import com.rabbiter.ol.service.ApplicantService;
import com.rabbiter.ol.service.UserClassService;
import com.rabbiter.ol.vo.ApplicantVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * @author 
 * @email ${email}
 * @date 2024-02-13 00:22:11
 */
@RestController
@RequestMapping("study/applicant")
public class ApplicantController {
    @Autowired
    private ApplicantService applicantService;


    @Autowired
    private UserClassService userClassService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestBody ApplicantVo applicantVo) {
        applicantVo.setPage((applicantVo.getPage() - 1) * applicantVo.getPageSize());
        Map<String, Object> page = applicantService.queryPage(applicantVo);
        return Result.success(page);
    }

    /**
     * 列表
     */
    @RequestMapping("/joinTrueOrFalse")
    public Result joinTrueOrFalse(@RequestBody ApplicantVo applicantVo) {
        QueryWrapper<UserClassEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", applicantVo.getUserId());
        List<UserClassEntity> list = userClassService.list(queryWrapper);

        List<ApplicantEntity> list1 = applicantService.list(new QueryWrapper<ApplicantEntity>().
                eq("user_id", applicantVo.getUserId())
                .eq("status", 1));
        return Result.success((list.size() + list1.size()) != 0);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Integer id) {
        ApplicantEntity applicant = applicantService.getById(id);
        return Result.success(applicant);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody ApplicantEntity applicant) {

        applicant.setCreateTime(new Date(System.currentTimeMillis()));
        boolean save = applicantService.save(applicant);
        if (save) {
            return Result.successCode();
        }
        return Result.failureCode();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody ApplicantVo applicantVo) {
        ApplicantEntity applicantEntity = new ApplicantEntity();
        applicantEntity.setStatus(applicantVo.getStatus());
        applicantEntity.setId(applicantVo.getId());
        boolean b = applicantService.updateById(applicantEntity);
        if (b) {
            if (applicantVo.getStatus() == 2) {
                QueryWrapper<UserClassEntity> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("user_id", applicantVo.getUserId());
                List<UserClassEntity> list = userClassService.list(queryWrapper);
                if (list.size() > 0) {
                    return Result.failure("该学生已经加入了班级");
                }
                UserClassEntity userClassEntity = new UserClassEntity();
                userClassEntity.setClassId(applicantVo.getClassId());
                userClassEntity.setUserId(applicantVo.getUserId());
                userClassService.save(userClassEntity);
            }
            return Result.successCode();
        }
        return Result.failureCode();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Integer[] ids) {
        applicantService.removeByIds(Arrays.asList(ids));
        return Result.successCode();
    }

}
