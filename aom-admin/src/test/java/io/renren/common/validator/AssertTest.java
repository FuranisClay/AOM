package io.renren.common.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import io.renren.common.exception.RRException;

class AssertTest {

    @Test
    void testIsBlank() {
        // Should not throw for non-blank string
        Assert.isBlank("not blank", "message");

        // Should throw for blank string
        assertThrows(RRException.class, () -> {
            Assert.isBlank("", "message");
        });
    }

    @Test
    void testIsNull() {
        // Should not throw for non-null object
        Assert.isNull(new Object(), "message");

        // Should throw for null
        assertThrows(RRException.class, () -> {
            Assert.isNull(null, "message");
        });
    }
}