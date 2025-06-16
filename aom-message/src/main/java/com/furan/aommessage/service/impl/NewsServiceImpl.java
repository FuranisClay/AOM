package com.furan.aommessage.service.impl;

import com.furan.aomcommon.service.impl.RedissonServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.furan.aommessage.dao.NewsDao;
import com.furan.aommessage.entity.NewsEntity;
import com.furan.aommessage.service.NewsService;

import javax.annotation.Resource;


@Service("newsService")
public class NewsServiceImpl extends ServiceImpl<NewsDao, NewsEntity> implements NewsService {

    @Resource
    RedissonServiceImpl redissonService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        String cacheKey = "MESSAGE" +  key;
        IPage<NewsEntity> page = redissonService.getValue(cacheKey);
        if (page != null) {
            return new PageUtils(page);
        }else{
            page = this.page(
                    new Query<NewsEntity>().getPage(params),
                    new QueryWrapper<NewsEntity>().
                            like(StringUtils.isNotBlank(key),"title", key)
            );
            redissonService.setValue(cacheKey, page,1000);
            return new PageUtils(page);
        }
    }

}
