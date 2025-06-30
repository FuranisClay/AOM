package io.renren.datasource.config;

import io.renren.datasource.properties.DataSourceProperties;
import io.renren.datasource.properties.DynamicDataSourceProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import javax.sql.DataSource;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DynamicDataSourceConfigTest {

    @MockBean
    private DynamicDataSourceProperties properties;

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Autowired
    private DynamicDataSource dynamicDataSource;

    @Test
    void testDynamicDataSourceInitialization() {
        assertNotNull(dynamicDataSource);
        assertNotNull(dataSourceProperties);

        // 默认数据源应该已设置

    }
}