package io.renren.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import javax.servlet.DispatcherType;
import static org.junit.jupiter.api.Assertions.*;

class FilterConfigTest {

    @Test
    void testFilterBeans() {
        try (AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext()) {
            context.register(FilterConfig.class);
            context.setServletContext(new MockServletContext());
            context.refresh();

            // Test shiro filter registration
            FilterRegistrationBean shiroFilter = context.getBean("shiroFilterRegistration", FilterRegistrationBean.class);
            assertNotNull(shiroFilter);
            assertEquals(Integer.MAX_VALUE - 1, shiroFilter.getOrder());
            assertTrue(shiroFilter.isEnabled());

            // Test XSS filter registration
            FilterRegistrationBean xssFilter = context.getBean("xssFilterRegistration", FilterRegistrationBean.class);
            assertNotNull(xssFilter);
            assertEquals(Integer.MAX_VALUE, xssFilter.getOrder());
            assertTrue(xssFilter.setDispatcherTypes().contains(DispatcherType.REQUEST));
        }
    }
}