package io.renren.config;

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
    void testPaginationInterceptor() {
        assertNotNull(paginationInterceptor);
        // Can't test internal behavior without actual database
    }
}