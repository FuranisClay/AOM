package io.renren.datasource.aspect;

import io.renren.datasource.annotation.DataSource;
import io.renren.datasource.config.DynamicContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataSourceAspectTest {

    @Mock
    private ProceedingJoinPoint joinPoint;

    @Mock
    private MethodSignature signature;

    private final DataSourceAspect aspect = new DataSourceAspect();

    @AfterEach
    void tearDown() {
        DynamicContextHolder.poll();
    }

    @Test
    void testClassLevelDataSource() throws Throwable {
        // 准备测试类和方法
        Class<?> targetClass = ClassWithDataSource.class;
        Method method = targetClass.getMethod("methodWithoutDataSource");

        // 设置mock行为
        when(joinPoint.getTarget()).thenReturn(new ClassWithDataSource());
        when(joinPoint.getSignature()).thenReturn(signature);
        when(signature.getMethod()).thenReturn(method);
        when(joinPoint.proceed()).thenReturn("result");

        // 执行切面
        Object result = aspect.around(joinPoint);

        // 验证
        assertEquals("result", result);
        assertNull(DynamicContextHolder.peek()); // 应该已经被清理
        verify(joinPoint).proceed();
    }

    @Test
    void testMethodLevelDataSource() throws Throwable {
        // 准备测试类和方法
        Class<?> targetClass = ClassWithoutDataSource.class;
        Method method = targetClass.getMethod("methodWithDataSource");

        // 设置mock行为
        when(joinPoint.getTarget()).thenReturn(new ClassWithoutDataSource());
        when(joinPoint.getSignature()).thenReturn(signature);
        when(signature.getMethod()).thenReturn(method);
        when(joinPoint.proceed()).thenReturn("result");

        // 执行切面
        Object result = aspect.around(joinPoint);

        // 验证
        assertEquals("result", result);
        assertNull(DynamicContextHolder.peek()); // 应该已经被清理
        verify(joinPoint).proceed();
    }

    @Test
    void testMethodOverrideClassDataSource() throws Throwable {
        // 准备测试类和方法
        Class<?> targetClass = ClassWithDataSource.class;
        Method method = targetClass.getMethod("methodWithDataSource");

        // 设置mock行为
        when(joinPoint.getTarget()).thenReturn(new ClassWithDataSource());
        when(joinPoint.getSignature()).thenReturn(signature);
        when(signature.getMethod()).thenReturn(method);
        when(joinPoint.proceed()).thenReturn("result");

        // 执行切面
        Object result = aspect.around(joinPoint);

        // 验证
        assertEquals("result", result);
        assertNull(DynamicContextHolder.peek()); // 应该已经被清理
        verify(joinPoint).proceed();
    }

    // 测试类
    @DataSource("classDB")
    private static class ClassWithDataSource {
        public void methodWithoutDataSource() {}

        @DataSource("methodDB")
        public void methodWithDataSource() {}
    }

    private static class ClassWithoutDataSource {
        @DataSource("methodDB")
        public void methodWithDataSource() {}
    }
}