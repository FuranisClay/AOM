package com.furan.mettings.service.impl;

import com.furan.aomcommon.service.impl.RedissonServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.furan.mettings.dao.MeetingsDao;
import com.furan.mettings.entity.MeetingsEntity;
import com.furan.mettings.service.MeetingsService;

import javax.annotation.Resource;


@Service("meetingsService")
public class MeetingsServiceImpl extends ServiceImpl<MeetingsDao, MeetingsEntity> implements MeetingsService {

    @Resource
    RedissonServiceImpl redissonService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        String cacheKey = "MEETINGS" +  key;
        IPage<MeetingsEntity> page = redissonService.getValue(cacheKey);
        if (page != null) {
            return new PageUtils(page);
        }else{
            page = this.page(
                    new Query<MeetingsEntity>().getPage(params),
                    new QueryWrapper<MeetingsEntity>().like(StringUtils.isNotBlank(key),"meeting_name", key)
            );
            redissonService.setValue(cacheKey, page,1000);
            return new PageUtils(page);
        }
    }

}
