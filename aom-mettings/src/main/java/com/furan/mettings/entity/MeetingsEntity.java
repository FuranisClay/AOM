package com.furan.mettings.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 会议信息表
 *
 * @author furan
 * @email furan@gmail.com
 * @date 2025-06-26 19:35:06
 */
@Data
@TableName("meetings")
public class MeetingsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer id;
	/**
	 * 会议名称 (from news.title)
	 */
	private String meetingName;
	/**
	 * 会议简介 (from news.summary)
	 */
	private String meetingSummary;
	/**
	 * 会议内容/议程 (from news.content)
	 */
	private String meetingContent;
	/**
	 * 会议封面图片路径 (from news.image_url)
	 */
	private String coverUrl;
	/**
	 * 创建者ID (from news.author_id)
	 */
	private Integer creatorId;
	/**
	 * 创建者姓名 (from news.author_name)
	 */
	private String creatorName;
	/**
	 * 会议状态：草稿/待审核/已批准/已拒绝 (from news.status)
	 */
	private String status;
	/**
	 * 参与人数 (from news.view_count)
	 */
	private Integer participantCount;
	/**
	 * 会议开始时间
	 */
	private Date startTime;
	/**
	 * 会议结束时间
	 */
	private Date endTime;
	/**
	 * 创建时间
	 */
	private Date createdAt;
	/**
	 * 更新时间
	 */
	private Date updatedAt;

}
