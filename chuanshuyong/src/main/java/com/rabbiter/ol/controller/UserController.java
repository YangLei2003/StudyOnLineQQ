package com.rabbiter.ol.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rabbiter.ol.common.Result;
import com.rabbiter.ol.dao.UserClassDao;
import com.rabbiter.ol.dao.UserDao;
import com.rabbiter.ol.entity.UserClassEntity;
import com.rabbiter.ol.entity.UserEntity;
import com.rabbiter.ol.entity.UserRoleEntity;
import com.rabbiter.ol.entity.UserSubjectEntity;
import com.rabbiter.ol.service.UserClassService;
import com.rabbiter.ol.service.UserRoleService;
import com.rabbiter.ol.service.UserService;
import com.rabbiter.ol.service.UserSubjectService;
import com.rabbiter.ol.vo.LoginVo;
import com.rabbiter.ol.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author
 * @email ${email}
 * @date 2024-02-12 00:24:20
 */
@RestController
@RequestMapping("study/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserClassService userClassService;

    @Autowired
    private UserSubjectService userSubjectService;

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserClassDao userClassDao;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestBody UserVo userVo) {
        userVo.setPage((userVo.getPage() - 1) * userVo.getPageSize());
        Map<String, Object> page = userService.queryPage(userVo);
        return Result.success(page);
    }

    /**
     * 列表
     */
    @RequestMapping("/findList")
    public Result findList(@RequestBody UserVo userVo) {
        List<HashMap> page = userService.findList(userVo);
        return Result.success(page);
    }

    /**
     * 列表
     */
    @RequestMapping("/findNotDoWork")
    public Result findNotDoWork(@RequestBody UserVo userVo) {
        userVo.setPage((userVo.getPage() - 1) * userVo.getPageSize());
        Map<String, Object> page = userService.findNotDoWork(userVo);
        return Result.success(page);
    }

    /**
     * 列表
     */
    @RequestMapping("/findNotDoHomework")
    public Result findNotDoHomework(@RequestBody UserVo userVo) {
        userVo.setPage((userVo.getPage() - 1) * userVo.getPageSize());
        Map<String, Object> page = userService.findNotDoHomework(userVo);
        return Result.success(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info")
    public Result info(@RequestBody UserEntity userEntity) throws ParseException {
        UserEntity user = userService.getById(userEntity.getId());
        return Result.success(user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody UserVo user) {
        if (userService.getBaseMapper().selectCount(new QueryWrapper<UserEntity>().eq("account", user.getAccount())) != 0) {
            // 账号重复
            return Result.failureCode();
        }

        user.getUserEntity().setCreateTime(new Date(System.currentTimeMillis()));
        boolean save = userService.save(user.getUserEntity());

        //新增角色
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRoleId(user.getRoleId());
        userRoleEntity.setUserId(user.getUserEntity().getId());
        userRoleService.save(userRoleEntity);

        //添加学生与班级关系  学生和班级一对一
        if (user.getClassId() != null) {
            UserClassEntity userClassEntity = new UserClassEntity();
            userClassEntity.setClassId(user.getClassId());
            userClassEntity.setUserId(user.getUserEntity().getId());
            userClassService.save(userClassEntity);
        }

        if (user.getSubjectId() != null) {
            UserSubjectEntity userSubjectEntity = new UserSubjectEntity();
            userSubjectEntity.setSubjectId(user.getSubjectId());
            userSubjectEntity.setUserId(user.getUserEntity().getId());
            userSubjectService.save(userSubjectEntity);
        }

        if (save) {
            return Result.successCode();
        }
        return Result.failureCode();
    }

    /**
     * 用户注册
     */
    @RequestMapping("/registry")
    public Result registry(@RequestBody UserEntity user) {
        if (userService.getBaseMapper().selectCount(new QueryWrapper<UserEntity>().eq("account", user.getAccount())) != 0) {
            // 账号重复
            return Result.failureCode();
        }
        user.setCreateTime(new Date(System.currentTimeMillis()));
        boolean save = userService.save(user);
        if (save) {
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserId(user.getId());
            userRoleEntity.setRoleId(3);
            userRoleService.save(userRoleEntity);
            return Result.successCode();
        }
        return Result.failureCode();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody UserVo userVo) {
        boolean b;
        if (userVo.getUserEntity() == null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(userVo.getId());
            userEntity.setUserName(userVo.getUserName());
            userEntity.setSex(userVo.getSex());
            userEntity.setPhone(userVo.getPhone());
            b = userService.updateById(userEntity);
        } else {
            b = userService.updateById(userVo.getUserEntity());
        }
        if (b) {
            if (userVo.getClassId() != null) {
                //根据老师ID删除老师所对应的班级并重新新增
                if (userVo.getUserEntity() != null) {
                    userClassDao.remove(userVo.getUserEntity().getId());
                } else {
                    userClassDao.remove(userVo.getId());
                }
                UserClassEntity userClassEntity = new UserClassEntity();
                userClassEntity.setClassId(userVo.getClassId());
                if (userVo.getUserEntity() != null) {
                    userClassEntity.setUserId(userVo.getUserEntity().getId());
                } else {
                    userClassEntity.setUserId(userVo.getId());
                }
                userClassService.save(userClassEntity);
            }


            if (userVo.getSubjectId() != null) {
                //根据老师ID删除老师所对应的科目并重新新增
                QueryWrapper<UserSubjectEntity> userSubjectQueryWrapper = new QueryWrapper<>();
                userSubjectQueryWrapper.eq("user_id", userVo.getUserEntity().getId());
                userSubjectService.remove(userSubjectQueryWrapper);

                UserSubjectEntity userSubjectEntity = new UserSubjectEntity();
                userSubjectEntity.setSubjectId(userVo.getSubjectId());
                userSubjectEntity.setUserId(userVo.getUserEntity().getId());
                userSubjectService.save(userSubjectEntity);
            }
            return Result.successCode();
        }
        return Result.failureCode();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody UserEntity user) {
        //删除用户信息
        userService.removeById(user.getId());

        //删除对应角色
        QueryWrapper<UserRoleEntity> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.eq("user_id", user.getId());
        userRoleService.remove(userRoleQueryWrapper);

        //删除对应班级
        QueryWrapper<UserClassEntity> userClassQueryWrapper = new QueryWrapper<>();
        userClassQueryWrapper.eq("user_id", user.getId());
        userClassService.remove(userClassQueryWrapper);

        return Result.successCode();
    }


    /**
     * 登录
     */
    @RequestMapping("/login")
    public Result login(@RequestBody LoginVo loginVo) {
        List<HashMap> users = userService.login(loginVo);
        if (users.size() < 1) {
            return Result.failure("登录失败");
        }

        return Result.success(users.get(0));
    }

    /**
     * 修改
     */
    @RequestMapping("/updatePassword")
    public Result updatePassword(@RequestBody UserVo userVo) {
        UserEntity byId = userService.getById(userVo.getId());
        if (userVo.getPassword().equals(byId.getPassword())) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(userVo.getId());
            userEntity.setPassword(userVo.getNewPassword());
            boolean updateById = userService.updateById(userEntity);
            if (updateById) {
                return Result.successCode();
            }
            return Result.failureCode();
        }
        return Result.failureCode();
    }

}
