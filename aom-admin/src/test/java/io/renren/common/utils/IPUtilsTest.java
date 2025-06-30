package io.renren.common.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.servlet.http.HttpServletRequest;
import org.mockito.Mockito;

class IPUtilsTest {

    @Test
    void testGetIpAddr() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

        // Test X-Forwarded-For
        Mockito.when(request.getHeader("x-forwarded-for")).thenReturn("192.168.1.1");
        assertEquals("192.168.1.1", IPUtils.getIpAddr(request));

        // Test Proxy-Client-IP
        Mockito.when(request.getHeader("x-forwarded-for")).thenReturn(null);
        Mockito.when(request.getHeader("Proxy-Client-IP")).thenReturn("192.168.1.2");
        assertEquals("192.168.1.2", IPUtils.getIpAddr(request));

        // Test remote address
        Mockito.when(request.getHeader("x-forwarded-for")).thenReturn(null);
        Mockito.when(request.getHeader("Proxy-Client-IP")).thenReturn(null);
        Mockito.when(request.getRemoteAddr()).thenReturn("192.168.1.3");
        assertEquals("192.168.1.3", IPUtils.getIpAddr(request));
    }
}