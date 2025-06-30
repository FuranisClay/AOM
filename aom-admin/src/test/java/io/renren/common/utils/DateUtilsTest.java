package io.renren.common.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

class DateUtilsTest {

    @Test
    void testFormat() {
        Date now = new Date();
        String formatted = DateUtils.format(now, DateUtils.DATE_PATTERN);
        assertNotNull(formatted);
        assertEquals(10, formatted.length()); // yyyy-MM-dd

        String formattedTime = DateUtils.format(now, DateUtils.DATE_TIME_PATTERN);
        assertNotNull(formattedTime);
        assertEquals(19, formattedTime.length()); // yyyy-MM-dd HH:mm:ss
    }

    @Test
    void testStringToDate() {
        String dateStr = "2023-01-01";
        Date date = DateUtils.stringToDate(dateStr, DateUtils.DATE_PATTERN);
        assertNotNull(date);

        String dateTimeStr = "2023-01-01 12:00:00";
        Date dateTime = DateUtils.stringToDate(dateTimeStr, DateUtils.DATE_TIME_PATTERN);
        assertNotNull(dateTime);
    }

    @Test
    void testAddDateMethods() {
        Date now = new Date();

        // Test adding seconds
        Date future = DateUtils.addDateSeconds(now, 60);
        assertTrue(future.after(now));

        // Test adding days
        Date past = DateUtils.addDateDays(now, -1);
        assertTrue(past.before(now));
    }
}