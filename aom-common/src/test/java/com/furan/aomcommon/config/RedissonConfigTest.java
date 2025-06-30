package com.furan.aomcommon.config;

import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedissonConfigTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private RedissonClient redissonClient;

    @Test
    void testRedissonClientBeanExists() {
        assertTrue(context.containsBean("redissonClient"));
        assertNotNull(redissonClient);
    }

    @Test
    void testRedissonClientConfiguration() {
        RedissonConfig redissonConfig = new RedissonConfig();
        RedissonClient client = redissonConfig.redissonClient();

        assertNotNull(client);
        assertFalse(client.isShutdown());

        // 验证配置是否正确
        Config config = client.getConfig();
        assertNotNull(config);

        // 验证单节点配置
        assertEquals("redis://39.105.179.191:16379",
                config.useSingleServer().getAddress());
        assertEquals(0, config.useSingleServer().getDatabase());
        assertEquals(100, config.useSingleServer().getConnectionPoolSize());
        assertEquals(10, config.useSingleServer().getConnectionMinimumIdleSize());

        client.shutdown();
        assertTrue(client.isShutdown());
    }

    @Test
    void testRedissonClientShutdown() {
        RedissonConfig redissonConfig = new RedissonConfig();
        RedissonClient client = redissonConfig.redissonClient();


        assertTrue(client.isShutdown());
    }
}