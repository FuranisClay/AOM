package com.furan.aomcourses.service.impl;

import com.furan.aomcommon.service.impl.RedissonServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
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
        String key = (String)params.get("key");
        String cacheKey = "COURESE" +  key;
        IPage<CoursesEntity> page = redissonService.getValue(cacheKey);
        if (page != null) {
            return new PageUtils(page);
        }else{
            page = this.page(
                    new Query<CoursesEntity>().getPage(params),
                    new QueryWrapper<CoursesEntity>().like(StringUtils.isNotBlank(key),"course_name", key)
            );
            redissonService.setValue(cacheKey, page,1000);
            return new PageUtils(page);
        }
    }
}
