package com.furan.aommessage.dao;

import com.furan.aommessage.entity.NewsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 行业动态表
 * 
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-15 19:23:11
 */
@Mapper
public interface NewsDao extends BaseMapper<NewsEntity> {
	
}
