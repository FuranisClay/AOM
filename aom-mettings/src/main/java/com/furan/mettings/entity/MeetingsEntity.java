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
 * @date 2025-06-15 22:18:37
 */
@Data
@TableName("meetings")
public class MeetingsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 会议ID，主键
	 */
	@TableId
	private Integer id;
	/**
	 * 会议名称
	 */
	private String meetingName;
	/**
	 * 会议封面图片URL
	 */
	private String meetingCover;
	/**
	 * 会议内容
	 */
	private String meetingContent;
	/**
	 * 会议开始时间
	 */
	private Date startTime;
	/**
	 * 会议结束时间
	 */
	private Date endTime;
	/**
	 * 创建人
	 */
	private String creator;
	/**
	 * 创建人类型：1-管理员，2-企业用户
	 */
	private Integer creatorType;
	/**
	 * 审核状态：0-待审核，1-审核通过，2-审核不通过
	 */
	private Integer auditStatus;
	/**
	 * 审核人
	 */
	private String auditor;
	/**
	 * 审核时间
	 */
	private Date auditTime;
	/**
	 * 审核意见
	 */
	private String auditReason;
	/**
	 * 会议状态：0-已删除，1-正常
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
