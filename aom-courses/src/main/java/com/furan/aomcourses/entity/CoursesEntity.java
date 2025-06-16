package com.furan.aomcourses.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 课程信息表
 * 
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-15 22:02:57
 */
@Data
@TableName("courses")
public class CoursesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 课程ID，主键
	 */
	@TableId
	private Integer id;
	/**
	 * 课程名称
	 */
	private String courseName;
	/**
	 * 课程封面图片URL
	 */
	private String courseCover;
	/**
	 * 课程简介
	 */
	private String courseDescription;
	/**
	 * 课程排序（数字越小排序越靠前）
	 */
	private Integer courseSort;
	/**
	 * 课程视频URL
	 */
	private String courseVideo;
	/**
	 * 课程作者
	 */
	private String author;
	/**
	 * 课程状态：0-禁用，1-启用
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createdAt;
	/**
	 * 更新时间
	 */
	private Date updatedAt;

}
