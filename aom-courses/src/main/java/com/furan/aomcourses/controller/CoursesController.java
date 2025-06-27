package com.furan.aomcourses.controller;

import java.util.Arrays;
import java.util.Map;

import com.furan.common.utils.PageUtils;
import com.furan.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.furan.aomcourses.entity.CoursesEntity;
import com.furan.aomcourses.service.CoursesService;




/**
 * 课程信息表
 *
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-15 22:02:57
 */
@RestController
@RequestMapping("aomcourses/courses")
public class CoursesController {
    @Autowired
    private CoursesService coursesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("aomcourses:courses:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = coursesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("aomcourses:courses:info")
    public R info(@PathVariable("id") Integer id){
		CoursesEntity courses = coursesService.getById(id);

        return R.ok().put("courses", courses);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("aomcourses:courses:save")
    public R save(@RequestBody CoursesEntity courses) {
        coursesService.save(courses);
        return R.ok();
    }
    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("aomcourses:courses:update")
    public R update(@RequestBody CoursesEntity courses){
		coursesService.updateById(courses);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("aomcourses:courses:delete")
    public R delete(@RequestBody Integer[] ids){
		coursesService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    @RequestMapping("/list/pending")
    @RequiresPermissions("aomcourses:courses:list")
    public R listPending(@RequestParam Map<String, Object> params) {
        PageUtils page = coursesService.listJPending(params);
        return R.ok().put("page", page);
    }

    @RequestMapping("/list/author")
    @RequiresPermissions("aomcourses:courses:list")
    public R listByAuthorId(@RequestParam Map<String, Object> params) {
        PageUtils page = coursesService.queryPageByUserId(params);
        return R.ok().put("page", page);
    }


}
