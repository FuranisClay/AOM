package com.furan.aomcourses.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.furan.aomcourses.entity.CoursesEntity;
import io.renren.common.utils.PageUtils;

import java.util.Map;

/**
 * 课程信息表
 *
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-15 22:02:57
 */
public interface CoursesService extends IService<CoursesEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils listJPending(Map<String, Object> params);

    PageUtils queryPageByUserId(Map<String, Object> params);
}

