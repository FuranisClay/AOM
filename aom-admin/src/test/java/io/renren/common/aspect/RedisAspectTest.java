package io.renren.common.aspect;

import io.renren.common.exception.RRException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.aspectj.lang.ProceedingJoinPoint;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RedisAspectTest {

    @InjectMocks
    private RedisAspect redisAspect;

    @Mock
    private ProceedingJoinPoint joinPoint;

    @Test
    void testAroundWhenOpen() throws Throwable {
        redisAspect.open = true;
        when(joinPoint.proceed()).thenReturn("success");

        Object result = redisAspect.around(joinPoint);
        assertEquals("success", result);
    }

    @Test
    void testAroundWhenClosed() throws Throwable {
        redisAspect.open = false;

        Object result = redisAspect.around(joinPoint);
        assertNull(result);
        verify(joinPoint, never()).proceed();
    }

    @Test
    void testAroundWithException() throws Throwable {
        redisAspect.open = true;
        when(joinPoint.proceed()).thenThrow(new RuntimeException("Redis error"));

        assertThrows(RRException.class, () -> redisAspect.around(joinPoint));
    }
}