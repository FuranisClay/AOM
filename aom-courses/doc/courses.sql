-- 课程信息表
CREATE TABLE courses (
                         id INT PRIMARY KEY AUTO_INCREMENT COMMENT '课程ID，主键',
                         course_name VARCHAR(255) NOT NULL COMMENT '课程名称',
                         course_cover VARCHAR(500) COMMENT '课程封面图片URL',
                         course_description TEXT COMMENT '课程简介',
                         course_sort INT DEFAULT 0 COMMENT '课程排序（数字越小排序越靠前）',
                         course_video VARCHAR(500) COMMENT '课程视频URL',
                         author VARCHAR(100) COMMENT '课程作者',
                         status TINYINT DEFAULT 1 COMMENT '课程状态：0-禁用，1-启用',
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    -- 索引优化
                         INDEX idx_course_name (course_name),
                         INDEX idx_author (author),
                         INDEX idx_course_sort (course_sort),
                         INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程信息表';

-- 插入示例数据
INSERT INTO courses (course_name, course_cover, course_description, course_sort, course_video, author) VALUES
                                                                                                           ('Java基础编程', 'https://example.com/covers/java-basic.jpg', 'Java编程语言基础知识讲解，适合初学者', 1, 'https://example.com/videos/java-basic.mp4', '张老师'),
                                                                                                           ('Spring Boot实战', 'https://example.com/covers/springboot.jpg', '深入学习Spring Boot框架，构建企业级应用', 2, 'https://example.com/videos/springboot.mp4', '李老师'),
                                                                                                           ('数据库设计原理', 'https://example.com/covers/database.jpg', '数据库设计基础理论与实践应用', 3, 'https://example.com/videos/database.mp4', '王老师'),
                                                                                                           ('前端开发入门', 'https://example.com/covers/frontend.jpg', 'HTML、CSS、JavaScript基础知识', 4, 'https://example.com/videos/frontend.mp4', '赵老师');
