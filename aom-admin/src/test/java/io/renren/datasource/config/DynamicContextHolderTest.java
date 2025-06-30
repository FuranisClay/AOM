package io.renren.datasource.config;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DynamicContextHolderTest {

    @AfterEach
    void tearDown() {
        // 清理线程上下文
        while (DynamicContextHolder.peek() != null) {
            DynamicContextHolder.poll();
        }
    }

    @Test
    void testPushAndPeek() {
        DynamicContextHolder.push("db1");
        assertEquals("db1", DynamicContextHolder.peek());

        DynamicContextHolder.push("db2");
        assertEquals("db2", DynamicContextHolder.peek());
    }

    @Test
    void testPoll() {
        DynamicContextHolder.push("db1");
        DynamicContextHolder.push("db2");

        DynamicContextHolder.poll();
        assertEquals("db1", DynamicContextHolder.peek());

        DynamicContextHolder.poll();
        assertNull(DynamicContextHolder.peek());
    }

    @Test
    void testThreadIsolation() throws InterruptedException {
        DynamicContextHolder.push("mainThreadDB");

        Thread otherThread = new Thread(() -> {
            assertNull(DynamicContextHolder.peek());
            DynamicContextHolder.push("otherThreadDB");
            assertEquals("otherThreadDB", DynamicContextHolder.peek());
        });

        otherThread.start();
        otherThread.join();

        assertEquals("mainThreadDB", DynamicContextHolder.peek());
    }
}