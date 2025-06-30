package com.furan.aomcourses.service.impl;

import com.furan.aomcommon.service.impl.RedissonServiceImpl;
import com.furan.common.utils.PageUtils;
import com.furan.common.utils.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.furan.aomcourses.dao.CoursesDao;
import com.furan.aomcourses.entity.CoursesEntity;
import com.furan.aomcourses.service.CoursesService;

import javax.annotation.Resource;


@Service("coursesService")
public class CoursesServiceImpl extends ServiceImpl<CoursesDao, CoursesEntity> implements CoursesService {
    @Resource
    RedissonServiceImpl redissonService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        String author = (String) params.get("author");
        String sort = (String) params.get("sort");

        QueryWrapper<CoursesEntity> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(key)) wrapper.like("course_name", key);
        if (StringUtils.isNotBlank(author)) wrapper.like("author", author);
        if ("asc".equals(sort)) wrapper.orderByAsc("course_sort");
        else if ("desc".equals(sort)) wrapper.orderByDesc("course_sort");

        IPage<CoursesEntity> page = this.page(
                new Query<CoursesEntity>().getPage(params),
                wrapper
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils listJPending(Map<String, Object> params) {
        String cacheKey = "COURSEPENDING";
        IPage<CoursesEntity> page = redissonService.getValue(cacheKey);
        if (page != null) {
            return new PageUtils(page);
        } else {
            page = this.page(
                    new Query<CoursesEntity>().getPage(params),
                    new QueryWrapper<CoursesEntity>().eq("status", -1)
            );
            redissonService.setValue(cacheKey, page, 1000);
            return new PageUtils(page);
        }
    }



    /**
     * 按用户ID查询课程，并支持课程名、作者名和排序的任意组合筛选
     */
    @Override
    public PageUtils queryPageByUserId(Map<String, Object> params) {
        // 从参数中获取所有筛选条件
        String userId = (String) params.get("userId");
        String key = (String) params.get("key"); // 对应前端的 courseName
        String author = (String) params.get("author"); // 从参数中获取作者名
        String sort = (String) params.get("sort");

        // 构造一个更具体的缓存键，以避免不同查询条件返回相同缓存
        String cacheKey = "COURSEQUERYBYUSERID_" + userId +
                "_KEY_" + (key != null ? key : "") +
                "_AUTHOR_" + (author != null ? author : "") + // 新增
                "_SORT_" + (sort != null ? sort : "") +
                "_PAGE_" + params.get("page") +
                "_LIMIT_" + params.get("limit");

        IPage<CoursesEntity> page = redissonService.getValue(cacheKey);

        if (page != null) {
            return new PageUtils(page);
        }
        // 构造查询条件
        QueryWrapper<CoursesEntity> wrapper = new QueryWrapper<>();
        // 核心条件：按用户ID筛选
        wrapper.eq(StringUtils.isNotBlank(userId), "user_id", userId);
        // 附加条件：按课程名模糊搜索
        wrapper.like(StringUtils.isNotBlank(key), "course_name", key);
        // 新增：按作者名模糊搜索
        wrapper.like(StringUtils.isNotBlank(author), "author", author);
        // 附加条件：按排序字段排序
        if ("asc".equals(sort)) {
            wrapper.orderByAsc("course_sort");
        } else if ("desc".equals(sort)) {
            wrapper.orderByDesc("course_sort");
        }

        page = this.page(
                new Query<CoursesEntity>().getPage(params),
                wrapper
        );

        redissonService.setValue(cacheKey, page, 1000);
        return new PageUtils(page);
    }

}
