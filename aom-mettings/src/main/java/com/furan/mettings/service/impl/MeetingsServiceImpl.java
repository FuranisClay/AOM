package com.furan.mettings.service.impl;

import com.furan.aomcommon.service.impl.RedissonServiceImpl;
import com.furan.mettings.dao.MeetingsDao;
import com.furan.mettings.entity.MeetingsEntity;
import com.furan.mettings.service.MeetingsService;
import com.furan.common.utils.PageUtils;
import com.furan.common.utils.Query;
import com.furan.modules.api.SysUserApiService;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;


@Service("meetingsService")
public class MeetingsServiceImpl extends ServiceImpl<MeetingsDao, MeetingsEntity> implements MeetingsService {

    @Resource
    RedissonServiceImpl redissonService;

    @DubboReference
    private SysUserApiService sysUserApiService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");
        // Ensure key is not null to prevent NullPointerException in cache key
        String cacheKey = "MEETINGS_" + (key != null ? key : "");
        IPage<MeetingsEntity> page = redissonService.getValue(cacheKey);
        if (page != null) {
            return new PageUtils(page);
        }else{
            page = this.page(
                    new Query<MeetingsEntity>().getPage(params),
                    new QueryWrapper<MeetingsEntity>()
                            .like(StringUtils.isNotBlank(key),"meeting_name", key)
            );
            redissonService.setValue(cacheKey, page,1000);
            return new PageUtils(page);
        }
    }

    @Override
    public PageUtils listJPending(Map<String, Object> params) {

        String key = "pending";
        String cacheKey = "MEETINGSPENDING"  + key;
        IPage<MeetingsEntity> page = redissonService.getValue(cacheKey);
        if (page != null) {
            return new PageUtils(page);
        } else {
            page = this.page(
                    new Query<MeetingsEntity>().getPage(params),
                    new QueryWrapper<MeetingsEntity>()
                            .eq(StringUtils.isNotBlank(key), "status", key)
            );
            redissonService.setValue(cacheKey, page, 20000);
            return new PageUtils(page);
        }
    }

    @Override
    public PageUtils queryPageByUserId(Map<String, Object> params) {
        String key = (String) params.get("userId");
        String cacheKey = "MEETINGSQUERYBYUSERID_" + key;
        IPage<MeetingsEntity> page = redissonService.getValue(cacheKey);
        if (page != null) {
            return new PageUtils(page);
        } else {
            page = this.page(
                    new Query<MeetingsEntity>().getPage(params),
                    new QueryWrapper<MeetingsEntity>()
                            . eq(StringUtils.isNotBlank(key), "creator_id", key)
            );
            redissonService.setValue(cacheKey, page, 1000);
            return new PageUtils(page);
        }
    }

    @Override
    public PageUtils queryPageByTime(Map<String, Object> params) {
        String userIdStr = (String) params.get("userId");
        Long userId = userIdStr == null ? null : Long.valueOf(userIdStr);

        String startTimeStr = (String) params.get("startTime");
        String endTimeStr = (String) params.get("endTime");

        Date startTime = null;
        Date endTime = null;

        try {
            if (startTimeStr != null && !startTimeStr.isEmpty()) {
                Instant instant = Instant.parse(startTimeStr);
                startTime = Date.from(instant);
            }
            if (endTimeStr != null && !endTimeStr.isEmpty()) {
                Instant instant = Instant.parse(endTimeStr);
                endTime = Date.from(instant);
            }
        } catch (DateTimeParseException e) {
            throw new RuntimeException("时间格式解析错误，必须是ISO 8601格式", e);
        }

        Long roleId = sysUserApiService.queryRoleById(userId);

        QueryWrapper<MeetingsEntity> queryWrapper = new QueryWrapper<>();

        if (startTime != null) {
            queryWrapper.ge("start_time", startTime);
        }
        if (endTime != null) {
            queryWrapper.le("end_time", endTime);
        }

        IPage<MeetingsEntity> page;

        if (roleId == 1 || roleId == 3) {
            // 管理员/组长：查所有
            page = this.page(
                    new Query<MeetingsEntity>().getPage(params),
                    queryWrapper.orderByDesc("created_at")
            );
        } else {
            // 普通用户：只能查自己发起的
            queryWrapper.eq("creator_id", userId);
            page = this.page(
                    new Query<MeetingsEntity>().getPage(params),
                    queryWrapper.orderByDesc("created_at")
            );
        }

        return new PageUtils(page);
    }


}
