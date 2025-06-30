package com.furan.aommessage.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.StringRedisSerializer;

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
        assertNotNull(redisTemplate, "RedisTemplate应被成功创建");

        // 验证序列化器配置
        assertEquals(StringRedisSerializer.class, redisTemplate.getKeySerializer().getClass(),
                "Key序列化器应为StringRedisSerializer");
        assertEquals(StringRedisSerializer.class, redisTemplate.getValueSerializer().getClass(),
                "Value序列化器应为StringRedisSerializer");
    }

    @Test
    void testRedisOperationsBeans() {
        assertAll("所有Redis操作Bean应被成功创建",
                () -> assertNotNull(hashOperations, "HashOperations应被创建"),
                () -> assertNotNull(valueOperations, "ValueOperations应被创建"),
                () -> assertNotNull(listOperations, "ListOperations应被创建"),
                () -> assertNotNull(setOperations, "SetOperations应被创建"),
                () -> assertNotNull(zSetOperations, "ZSetOperations应被创建")
        );
    }

    @Test
    void testBasicRedisOperations() {
        // 测试基本操作是否正常工作
        String key = "test:config:key";
        String value = "test config value";

        // 测试字符串操作
        valueOperations.set(key, value);
        assertEquals(value, valueOperations.get(key), "应能正确设置和获取值");

        // 清理测试数据
        redisTemplate.delete(key);
        assertNull(valueOperations.get(key), "删除后应返回null");
    }
}