package com.furan.aomcourses.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisConfigTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    @Autowired
    private ValueOperations<String, String> valueOperations;

    @Autowired
    private ListOperations<String, Object> listOperations;

    @Autowired
    private SetOperations<String, Object> setOperations;

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    @Test
    void testRedisTemplateConfiguration() {
        assertNotNull(redisTemplate);
        // 验证序列化器配置

    }

    @Test
    void testRedisOperationsBeans() {
        assertNotNull(hashOperations);
        assertNotNull(valueOperations);
        assertNotNull(listOperations);
        assertNotNull(setOperations);
        assertNotNull(zSetOperations);
    }

    @Test
    void testBasicRedisOperations() {
        // 测试基本操作是否正常工作
        String key = "test:key";
        String value = "test value";

        valueOperations.set(key, value);
        assertEquals(value, valueOperations.get(key));

        redisTemplate.delete(key);
        assertNull(valueOperations.get(key));
    }
}