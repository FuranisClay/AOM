-- 行业动态表设计（不包含用户表）

-- 行业动态表
CREATE TABLE news (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      title VARCHAR(200) NOT NULL COMMENT '标题',
                      summary TEXT COMMENT '新闻简介',
                      content LONGTEXT NOT NULL COMMENT '内容',
                      image_url VARCHAR(500) COMMENT '新闻图片路径',
                      author_id INT NOT NULL COMMENT '作者ID',
                      author_name VARCHAR(50) NOT NULL COMMENT '作者姓名',
                      status ENUM('draft', 'pending', 'approved', 'rejected') NOT NULL DEFAULT 'draft' COMMENT '状态：草稿/待审核/已通过/已拒绝',
                      view_count INT DEFAULT 0 COMMENT '浏览次数',
                      published_at TIMESTAMP NULL COMMENT '发布时间',
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

                      INDEX idx_title (title),
                      INDEX idx_author_id (author_id),
                      INDEX idx_author_name (author_name),
                      INDEX idx_status (status),
                      INDEX idx_published_at (published_at),
                      FULLTEXT idx_search (title, summary, author_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='行业动态表';

-- 插入测试数据
INSERT INTO news (title, summary, content, image_url, author_id, author_name, status, view_count, published_at) VALUES
                                                                                                                    ('人工智能技术发展趋势',
                                                                                                                     'AI技术在各行业应用现状分析，探讨未来发展方向和机遇挑战',
                                                                                                                     '人工智能技术正在快速发展，在制造业、金融业、医疗等领域都有广泛应用。机器学习、深度学习等技术不断突破，为各行业带来革命性变化。本文深入分析当前AI技术应用现状，并展望未来发展趋势...',
                                                                                                                     '/uploads/images/ai_trend.jpg',
                                                                                                                     1001, '企业用户张三', 'approved', 1200, '2024-12-01 10:30:00'),

                                                                                                                    ('新能源汽车市场报告',
                                                                                                                     '2024年新能源汽车销量分析，市场格局及发展前景',
                                                                                                                     '新能源汽车市场持续增长，电动汽车技术不断突破。2024年全球新能源汽车销量再创新高，中国市场占据重要地位。报告详细分析了各品牌市场表现、技术发展水平以及未来市场预测...',
                                                                                                                     '/uploads/images/ev_market.jpg',
                                                                                                                     1002, '企业用户李四', 'approved', 850, '2024-12-02 14:20:00'),

                                                                                                                    ('5G网络建设进展',
                                                                                                                     '全国5G基站建设最新数据，覆盖范围和应用场景分析',
                                                                                                                     '5G网络覆盖范围不断扩大，为数字经济发展提供基础支撑。目前全国5G基站数量已超过300万个，网络覆盖持续完善。5G技术在智慧城市、工业互联网、远程医疗等领域应用前景广阔...',
                                                                                                                     '/uploads/images/5g_progress.jpg',
                                                                                                                     1001, '企业用户张三', 'approved', 620, '2024-12-03 09:15:00'),

                                                                                                                    ('区块链应用案例分析',
                                                                                                                     '区块链在供应链管理中的应用，提升透明度和可追溯性',
                                                                                                                     '区块链技术在供应链透明度和可追溯性方面发挥重要作用。通过分布式账本技术，企业可以实现供应链全程监控，提高产品质量管控水平。本文通过实际案例分析区块链在供应链管理中的具体应用...',
                                                                                                                     '/uploads/images/blockchain.jpg',
                                                                                                                     2001, '普通用户王五', 'pending', 0, NULL),

                                                                                                                    ('云计算发展现状',
                                                                                                                     '企业上云趋势及挑战分析，云服务市场竞争格局',
                                                                                                                     '越来越多企业选择云计算服务，但仍面临安全和成本等挑战。云计算市场竞争激烈，各大云服务商纷纷推出创新产品。企业在选择云服务时需要综合考虑性能、安全、成本等多个因素...',
                                                                                                                     '/uploads/images/cloud_computing.jpg',
                                                                                                                     2002, '普通用户赵六', 'rejected', 0, NULL),

                                                                                                                    ('物联网技术应用',
                                                                                                                     '智慧城市建设中的物联网应用，推动城市数字化转型',
                                                                                                                     '物联网技术在智慧交通、智能安防等领域应用广泛。通过传感器网络和数据分析，城市管理效率显著提升。物联网设备数量快速增长，为智慧城市建设提供了强有力的技术支撑...',
                                                                                                                     '/uploads/images/iot_smart_city.jpg',
                                                                                                                     1002, '企业用户李四', 'approved', 380, '2024-12-04 16:45:00'),

                                                                                                                    ('大数据分析技术趋势',
                                                                                                                     '企业大数据应用现状，数据驱动决策的重要性',
                                                                                                                     '大数据技术在企业决策中发挥越来越重要的作用。通过数据分析，企业可以更好地了解市场趋势和客户需求。实时数据处理、机器学习算法等技术不断发展，为企业提供更精准的数据洞察...',
                                                                                                                     '/uploads/images/big_data.jpg',
                                                                                                                     1001, '企业用户张三', 'approved', 520, '2024-12-05 11:20:00'),

                                                                                                                    ('网络安全防护策略',
                                                                                                                     '企业网络安全威胁分析，防护措施和应对方案',
                                                                                                                     '网络安全威胁日益复杂，企业需要建立完善的安全防护体系。从技术防护到管理制度，全方位保障企业数据安全。本文分析当前主要网络安全威胁，并提出相应的防护策略和解决方案...',
                                                                                                                     '/uploads/images/cyber_security.jpg',
                                                                                                                     1003, '企业用户陈七', 'pending', 0, NULL);
