package com.furan.mettings.dao;

import com.furan.mettings.entity.MeetingsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会议信息表
 * 
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-15 22:18:37
 */
@Mapper
public interface MeetingsDao extends BaseMapper<MeetingsEntity> {
	
}
