package io.renren.config;

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
    private ValueOperations<String, String> valueOperations;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    @Autowired
    private ListOperations<String, Object> listOperations;

    @Autowired
    private SetOperations<String, Object> setOperations;

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    @Test
    void testRedisTemplate() {
        assertNotNull(redisTemplate);
        assertEquals(StringRedisSerializer.class, redisTemplate.getKeySerializer().getClass());
    }

    @Test
    void testRedisOperations() {
        assertNotNull(valueOperations);
        assertNotNull(hashOperations);
        assertNotNull(listOperations);
        assertNotNull(setOperations);
        assertNotNull(zSetOperations);
    }
}