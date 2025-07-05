# 项目介绍
本项目是一个基于微服务架构的系统，主要由多个子模块组成，包括网关（aom-gateway）、通用模块（aom-common）、消息管理（aom-message）和会议管理（aom-mettings）。该项目采用 Spring Boot、MyBatis Plus、Redisson、Dubbo、ZooKeeper 等主流技术栈，构建了一个可扩展、高可用的企业级管理系统。

## 1. aom-common - 公共模块
   使用 Redisson 提供分布式缓存工具封装。
   定义统一的 Redis 操作接口 IRedisService，涵盖基本操作如 set、get、incr、lock、bloom filter、延迟队列等。
   实现了 RedissonServiceImpl，具体使用 Redisson 客户端进行操作。
   配置类 RedissonConfig 初始化 RedissonClient，并连接到远程 Redis 服务器。

   提供通用的 Redis 操作工具类。
   支持分布式锁、布隆过滤器、延迟队列、原子计数器等功能。
   可作为其他模块的依赖库，减少重复开发。

## 2. aom-gateway - 网关模块
   使用 Spring Cloud Gateway 构建 API 网关。
   配置路由规则将请求转发至对应的后端服务（如 aom-message, aom-courses, aom-meetings, aom-admin）。
   集成 CORS 解决跨域问题。
   使用 YAML 配置文件定义路由策略和日志级别。


## 3. aom-message - 消息管理模块
   基于 MyBatis Plus 实现数据库操作。
   使用 MySQL 存储行业动态数据。
   Redis 缓存热点数据，提高查询效率。
   使用 PageUtils 分页封装。

   列表展示（支持关键字搜索）
   新增/修改/删除
   按状态筛选待审核项
   按用户 ID 查询文章
   Redis 缓存优化查询性能

## 4. aom-mettings - 会议管理模块
   使用 Dubbo + ZooKeeper 实现服务注册与发现。
   MyBatis Plus 分页插件 + Redis 缓存。
   结构设计与 news 表类似，用于存储会议信息。


## 4. aom-mettings - 会议管理模块
   技术点：
   使用 Dubbo + ZooKeeper 实现服务注册与发现。
   MyBatis Plus 分页插件 + Redis 缓存。
   结构设计与 news 表类似，用于存储会议信息。
   功能模块：
   会议列表（支持关键字搜索）
   待审核会议列表
   按用户 ID 查询会议
   按时间范围查询会议（管理员可见全部，普通用户仅可见自己发起的）

## Ai模块部署方案

启动docker-compose脚本并根据实际情况更改application.yml 配置文件

```text
# docker-comopse -f docker-compose-environment.yml up -d

version: '3'
services:

  # 对话模型

  # ollama pull deepseek-r1:1.5b

  # 运行模型

  # ollama run deepseek-r1:1.5b

  # 联网模型

  # ollama pull nomic-embed-text

  ollama:
    image: ollama/ollama:0.5.10
    container_name: ollama
    restart: unless-stopped
    ports:
      - "11434:11434"
  redis:
    image: redis:6.2
    container_name: redis
    restart: always
    hostname: redis
    privileged: true
    ports:
      - 16379:6379
    volumes:
      - ./redis/redis.conf:/usr/local/etc/redis/redis.conf
    command: redis-server /usr/local/etc/redis/redis.conf
    networks:
      - my-network
    healthcheck:
      test: [ "CMD", "redis-cli", "ping" ]
      interval: 10s
      timeout: 5s
      retries: 3

  # RedisAdmin https://github.com/joeferner/redis-commander

  # 账密 admin/admin

  redis-admin:
    image: spryker/redis-commander:0.8.0
    container_name: redis-admin
    hostname: redis-commander
    restart: always
    ports:

   - 8081:8081
     vironment:
        - REDIS_HOSTS=local:redis:6379
          HTTP_USER=admin
             - HTTP_PASSWORD=admin
               LANG=C.UTF-8
                  - LANGUAGE=C.UTF-8
                    LC_ALL=C.UTF-8
                        networks:
                       - my-network
                         pends_on:
                               redis:
                         condition: service_healthy

  vector_db:
    image: ankane/pgvector:v0.5.0
    container_name: vector_db
    restart: always
    environment:
      - POSTGRES_USER=postgres
      - POSTGRES_PASSWORD=postgres
      - POSTGRES_DB=springai
      - PGPASSWORD=postgres
    volumes:
      - ./pgvector/sql/init.sql:/docker-entrypoint-initdb.d/init.sql
    logging:
      options:
        max-size: 10m
        max-file: "3"
    ports:
      - '15432:5432'
    healthcheck:
      test: "pg_isready -U postgres -d vector_store"
      interval: 2s
      timeout: 20s
      retries: 10
    networks:
      - my-network

networks:
  my-network:
    driver: bridge
```



## 其他模块

运行 load maven project并修改配置文件
