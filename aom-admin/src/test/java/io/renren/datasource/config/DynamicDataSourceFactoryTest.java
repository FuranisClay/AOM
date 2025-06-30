package io.renren.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.renren.datasource.properties.DataSourceProperties;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DynamicDataSourceFactoryTest {

    @Test
    void testBuildDruidDataSource() {
        DataSourceProperties properties = new DataSourceProperties();
        properties.setDriverClassName("org.h2.Driver");
        properties.setUrl("jdbc:h2:mem:test");
        properties.setUsername("sa");
        properties.setPassword("");
        properties.setMaxActive(5);

        DruidDataSource dataSource = DynamicDataSourceFactory.buildDruidDataSource(properties);

        assertNotNull(dataSource);
        assertEquals("org.h2.Driver", dataSource.getDriverClassName());
        assertEquals("jdbc:h2:mem:test", dataSource.getUrl());
        assertEquals("sa", dataSource.getUsername());
        assertEquals(5, dataSource.getMaxActive());
    }
}