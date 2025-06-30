package com.furan.mettings.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Arrays;

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
        assertAll("序列化器配置验证",
                () -> assertEquals(StringRedisSerializer.class, redisTemplate.getKeySerializer().getClass()),
                () -> assertEquals(StringRedisSerializer.class, redisTemplate.getValueSerializer().getClass()),
                () -> assertEquals(StringRedisSerializer.class, redisTemplate.getHashKeySerializer().getClass()),
                () -> assertEquals(StringRedisSerializer.class, redisTemplate.getHashValueSerializer().getClass())
        );
    }

    @Test
    void testRedisOperationsBeans() {
        assertAll("Redis操作接口验证",
                () -> assertNotNull(hashOperations, "HashOperations应被创建"),
                () -> assertNotNull(valueOperations, "ValueOperations应被创建"),
                () -> assertNotNull(listOperations, "ListOperations应被创建"),
                () -> assertNotNull(setOperations, "SetOperations应被创建"),
                () -> assertNotNull(zSetOperations, "ZSetOperations应被创建")
        );
    }

    @Test
    void testRedisBasicOperations() {
        // 测试字符串操作
        String key = "test:meeting:key";
        String value = "test meeting value";

        valueOperations.set(key, value);
        assertEquals(value, valueOperations.get(key), "应能正确设置和获取值");

        // 测试哈希操作
        String hashKey = "test:meeting:hash";
        String field = "field1";
        String hashValue = "hash value";
        hashOperations.put(hashKey, field, hashValue);
        assertEquals(hashValue, hashOperations.get(hashKey, field), "应能正确设置和获取哈希值");

        // 清理测试数据
        redisTemplate.delete(Arrays.asList(key, hashKey));
    }
}