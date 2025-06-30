package io.renren.datasource.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DynamicDataSourceTest {

    private DynamicDataSource dynamicDataSource;
    private DataSource defaultDataSource;
    private DataSource db1DataSource;
    private DataSource db2DataSource;

    @BeforeEach
    void setUp() {
        dynamicDataSource = new DynamicDataSource();

        // 创建模拟数据源
        defaultDataSource = mock(DataSource.class);
        db1DataSource = mock(DataSource.class);
        db2DataSource = mock(DataSource.class);

        // 设置目标数据源
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("db1", db1DataSource);
        targetDataSources.put("db2", db2DataSource);

        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(defaultDataSource);
        dynamicDataSource.afterPropertiesSet();
    }

    @Test
    void testDetermineCurrentLookupKey() {
        // 默认数据源
        assertSame(defaultDataSource, dynamicDataSource.determineTargetDataSource());

        // 切换数据源
        DynamicContextHolder.push("db1");
        assertSame(db1DataSource, dynamicDataSource.determineTargetDataSource());

        DynamicContextHolder.push("db2");
        assertSame(db2DataSource, dynamicDataSource.determineTargetDataSource());

        // 清理
        DynamicContextHolder.poll();
        DynamicContextHolder.poll();
    }

    @Test
    void testUnknownDataSourceFallbackToDefault() {
        DynamicContextHolder.push("unknownDB");
        assertSame(defaultDataSource, dynamicDataSource.determineTargetDataSource());
        DynamicContextHolder.poll();
    }
}