package com.furan.mettings.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MybatisPlusConfigTest {

    @Autowired
    private PaginationInterceptor paginationInterceptor;

    @Test
    void testPaginationInterceptorBeanCreation() {
        assertNotNull(paginationInterceptor, "分页插件应被成功创建");

        // 验证分页插件默认配置
    }
}