package io.renren.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KaptchaConfigTest {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Test
    void testKaptchaProducer() {
        assertNotNull(defaultKaptcha);

        // Verify properties
        String text = defaultKaptcha.createText();
        assertNotNull(text);
        assertTrue(text.length() >= 4); // Default length is 5
    }
}