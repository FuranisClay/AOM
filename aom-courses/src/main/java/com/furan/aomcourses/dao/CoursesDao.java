package com.furan.aomcourses.dao;

import com.furan.aomcourses.entity.CoursesEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 课程信息表
 * 
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-15 22:02:57
 */
@Mapper
public interface CoursesDao extends BaseMapper<CoursesEntity> {
	
}
