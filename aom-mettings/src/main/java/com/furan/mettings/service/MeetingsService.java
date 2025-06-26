package com.furan.mettings.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.furan.mettings.entity.MeetingsEntity;
import io.renren.common.utils.PageUtils;

import java.util.Map;

/**
 * 会议信息表
 *
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-15 22:18:37
 */
public interface MeetingsService extends IService<MeetingsEntity> {

    PageUtils queryPage(Map<String, Object> params);
    PageUtils listJPending(Map<String, Object> params);
    PageUtils queryPageByUserId(Map<String, Object> params);
    PageUtils queryPageByTime(Map<String, Object> params);
}

