-- 会议信息表
CREATE TABLE meetings (
                          id INT PRIMARY KEY AUTO_INCREMENT COMMENT '会议ID，主键',
                          meeting_name VARCHAR(255) NOT NULL COMMENT '会议名称',
                          meeting_cover VARCHAR(500) COMMENT '会议封面图片URL',
                          meeting_content TEXT NOT NULL COMMENT '会议内容',
                          start_time DATETIME NOT NULL COMMENT '会议开始时间',
                          end_time DATETIME NOT NULL COMMENT '会议结束时间',
                          creator VARCHAR(100) NOT NULL COMMENT '创建人',
                          creator_type TINYINT NOT NULL DEFAULT 1 COMMENT '创建人类型：1-管理员，2-企业用户',
                          audit_status TINYINT DEFAULT 0 COMMENT '审核状态：0-待审核，1-审核通过，2-审核不通过',
                          auditor VARCHAR(100) COMMENT '审核人',
                          audit_time DATETIME COMMENT '审核时间',
                          audit_reason TEXT COMMENT '审核意见',
                          STATUS TINYINT DEFAULT 1 COMMENT '会议状态：0-已删除，1-正常',
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    -- 索引优化
                          INDEX idx_meeting_name (meeting_name),
                          INDEX idx_creator (creator),
                          INDEX idx_start_time (start_time),
                          INDEX idx_end_time (end_time),
                          INDEX idx_audit_status (audit_status),
                          INDEX idx_status (STATUS),
                          INDEX idx_creator_type (creator_type)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='会议信息表';

-- 插入示例数据
INSERT INTO meetings (meeting_name, meeting_cover, meeting_content, start_time, end_time, creator, creator_type, audit_status) VALUES
                                                                                                                                   ('产品规划讨论会', 'https://example.com/covers/product-meeting.jpg', '讨论2024年产品发展规划和路线图', '2024-03-15 14:00:00', '2024-03-15 16:00:00', '张经理', 1, 1),
                                                                                                                                   ('技术分享会', 'https://example.com/covers/tech-share.jpg', '分享最新的前端开发技术和最佳实践', '2024-03-20 10:00:00', '2024-03-20 12:00:00', '李工程师', 2, 0),
                                                                                                                                   ('季度总结会议', 'https://example.com/covers/quarterly.jpg', '第一季度工作总结和第二季度计划', '2024-03-25 09:00:00', '2024-03-25 11:00:00', '王总监', 1, 1),
                                                                                                                                   ('客户需求评审', 'https://example.com/covers/customer.jpg', '评审客户提出的新功能需求', '2024-03-30 15:00:00', '2024-03-30 17:00:00', '赵主管', 2, 0);
