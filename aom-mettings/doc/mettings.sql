-- ===================================================================
-- 会议信息表 (meetings)
-- This schema is designed to mirror the logic and structure of the 'news' table.
-- ===================================================================

CREATE TABLE meetings (
    -- Core Fields
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          meeting_name VARCHAR(200) NOT NULL COMMENT '会议名称 (from news.title)',
                          meeting_summary TEXT COMMENT '会议简介 (from news.summary)',
                          meeting_content LONGTEXT NOT NULL COMMENT '会议内容/议程 (from news.content)',
                          cover_url VARCHAR(500) COMMENT '会议封面图片路径 (from news.image_url)',

    -- Creator/Author Fields
                          creator_id INT NOT NULL COMMENT '创建者ID (from news.author_id)',
                          creator_name VARCHAR(50) NOT NULL COMMENT '创建者姓名 (from news.author_name)',

    -- Status and Lifecycle Fields
                          STATUS ENUM('draft', 'pending', 'approved', 'rejected') NOT NULL DEFAULT 'draft' COMMENT '会议状态：草稿/待审核/已批准/已拒绝 (from news.status)',
                          participant_count INT DEFAULT 0 COMMENT '参与人数 (from news.view_count)',

    -- Time-related Fields (Logically adapted from published_at)
                          start_time DATETIME NOT NULL COMMENT '会议开始时间',
                          end_time DATETIME NOT NULL COMMENT '会议结束时间',

    -- Timestamps
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    -- Indexes for Performance
                          INDEX idx_meeting_name (meeting_name),
                          INDEX idx_creator_id (creator_id),
                          INDEX idx_creator_name (creator_name),
                          INDEX idx_status (STATUS),
                          INDEX idx_start_time (start_time),
                          INDEX idx_end_time (end_time),
                          FULLTEXT idx_search (meeting_name, meeting_summary, creator_name)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='会议信息表';


-- ===================================================================
-- 插入测试数据 (Sample Data)
-- ===================================================================
INSERT INTO meetings (meeting_name, meeting_summary, meeting_content, cover_url, creator_id, creator_name, STATUS, participant_count, start_time, end_time) VALUES
                                                                                                                                                                ('Q1产品战略评审会',
                                                                                                                                                                 '回顾第一季度产品表现，并规划第二季度的产品路线图。',
                                                                                                                                                                 '详细议程包括：1. Q1数据回顾与分析。2. 市场竞品动态。3. Q2产品开发重点与资源分配。4. 开放讨论与Q&A。',
                                                                                                                                                                 '/uploads/covers/q1_strategy.jpg',
                                                                                                                                                                 1001, '企业用户张三', 'approved', 25, '2025-01-15 10:00:00', '2025-01-15 12:00:00'),

                                                                                                                                                                ('2025年度营销计划启动会',
                                                                                                                                                                 '正式启动2025年度营销计划，明确各团队目标与关键任务。',
                                                                                                                                                                 '会议将讨论全年的市场推广策略、预算分配、渠道合作计划以及关键绩效指标（KPI）。',
                                                                                                                                                                 '/uploads/covers/marketing_kickoff.jpg',
                                                                                                                                                                 1002, '企业用户李四', 'approved', 42, '2025-01-20 14:00:00', '2025-01-20 16:30:00'),

                                                                                                                                                                ('新功能“智能助手”技术评审',
                                                                                                                                                                 '对即将开发的“智能助手”新功能进行技术架构和可行性评审。',
                                                                                                                                                                 '后端团队将介绍技术选型，前端团队将展示原型设计，QA团队将讨论测试策略。',
                                                                                                                                                                 '/uploads/covers/tech_review.jpg',
                                                                                                                                                                 1001, '企业用户张三', 'pending', 0, '2025-02-10 09:30:00', '2025-02-10 11:00:00'),

                                                                                                                                                                ('客户反馈与需求研讨会',
                                                                                                                                                                 '集中讨论近期收集到的客户反馈，并将其转化为具体的产品需求。',
                                                                                                                                                                 '客服、销售和产品团队将共同参与，旨在提升用户满意度和产品竞争力。',
                                                                                                                                                                 '/uploads/covers/customer_feedback.jpg',
                                                                                                                                                                 2001, '普通用户王五', 'pending', 0, '2025-02-18 15:00:00', '2025-02-18 16:00:00'),

                                                                                                                                                                ('关于服务器迁移的提案会议',
                                                                                                                                                                 '讨论是否将应用服务器从IDC迁移至公有云的提案。',
                                                                                                                                                                 '这是一个初步讨论，旨在评估成本、风险和收益。目前仅为草稿，待完善后提交审核。',
                                                                                                                                                                 '/uploads/covers/server_migration.jpg',
                                                                                                                                                                 2002, '普通用户赵六', 'draft', 0, '2025-03-05 10:00:00', '2025-03-05 11:30:00'),

                                                                                                                                                                ('团队建设活动策划会',
                                                                                                                                                                 '为下个季度的团队建设活动集思广益。',
                                                                                                                                                                 '由于预算问题，该活动策划已被拒绝，需要重新提交方案。',
                                                                                                                                                                 '/uploads/covers/team_building.jpg',
                                                                                                                                                                 1002, '企业用户李四', 'rejected', 0, '2025-03-12 14:00:00', '2025-03-12 15:00:00');
