package com.furan.aommessage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.furan.aommessage.entity.NewsEntity;
import com.furan.common.utils.PageUtils;

import java.util.Map;

/**
 * 行业动态表
 *
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-15 19:23:11
 */
public interface NewsService extends IService<NewsEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils listJPending(Map<String, Object> params);

    PageUtils queryPageByUserId(Map<String, Object> params);
}

