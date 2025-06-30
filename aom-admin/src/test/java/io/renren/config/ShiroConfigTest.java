package io.renren.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import io.renren.modules.sys.jwt.JWTRealm;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShiroConfigTest {

    @MockBean
    private JWTRealm jwtRealm;

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private ShiroFilterFactoryBean shiroFilter;

    @Test
    void testSecurityManager() {
        assertNotNull(securityManager);
    }

    @Test
    void testShiroFilter() {
        assertNotNull(shiroFilter);
        assertEquals(securityManager, shiroFilter.getSecurityManager());
    }
}