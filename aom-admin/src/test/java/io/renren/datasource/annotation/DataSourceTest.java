package io.renren.datasource.annotation;

import org.junit.jupiter.api.Test;
import java.lang.annotation.Annotation;
import static org.junit.jupiter.api.Assertions.*;

class DataSourceTest {

    @Test
    void testAnnotationProperties() {
        Annotation annotation = SampleClass.class.getAnnotation(DataSource.class);
        assertNotNull(annotation);
        assertEquals(DataSource.class, annotation.annotationType());

        DataSource dataSource = (DataSource) annotation;
        assertEquals("testDB", dataSource.value());
    }

    @DataSource("testDB")
    private static class SampleClass {}
}