package io.renren.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springfox.documentation.spring.web.plugins.Docket;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SwaggerConfigTest {

    @Autowired
    private Docket docket;

    @Test
    void testSwaggerDocket() {
        assertNotNull(docket);
        assertEquals("3.0.0", docket.getDocumentationType().getVersion());
    }
}