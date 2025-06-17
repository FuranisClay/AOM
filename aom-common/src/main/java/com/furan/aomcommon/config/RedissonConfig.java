package com.furan.aomcommon.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: R
 * @Author itmei
 * @Package com.furan.aomcourses.config
 * @Date 2025/6/17 18:39
 * @description:
 */
@Configuration
public class RedissonConfig {

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://39.105.179.191:16379")
                .setPassword(null)  // 如果有密码写这里
                .setDatabase(0)
                .setConnectionPoolSize(100)
                .setConnectionMinimumIdleSize(10);
        return Redisson.create(config);
    }
}
