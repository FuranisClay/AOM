package com.furan.aommessage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 行业动态表
 *
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-15 19:23:11
 */
@Data
@TableName("news")
public class NewsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 新闻简介
	 */
	private String summary;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 新闻图片路径
	 */
	private String imageUrl;
	/**
	 * 作者ID
	 */
	private Integer authorId;
	/**
	 * 作者姓名
	 */
	private String authorName;
	/**
	 * 状态：草稿/待审核/已通过/已拒绝
	 */
	private String status;
	/**
	 * 浏览次数
	 */
	private Integer viewCount;
	/**
	 * 发布时间
	 */
	private Date publishedAt;
	/**
	 * 创建时间
	 */
	private Date createdAt;
	/**
	 * 更新时间
	 */
	private Date updatedAt;

}
