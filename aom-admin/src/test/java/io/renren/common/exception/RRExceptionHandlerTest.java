package io.renren.common.exception;

import com.furan.common.exception.RRException;
import com.furan.common.exception.RRExceptionHandler;
import com.furan.common.utils.R;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.dao.DuplicateKeyException;
import static org.junit.jupiter.api.Assertions.*;

class RRExceptionHandlerTest {

    private RRExceptionHandler handler = new RRExceptionHandler();

    @Test
    void testHandleRRException() {
        RRException ex = new RRException("Test error", 400);
        R result = handler.handleRRException(ex);

        assertEquals(400, result.get("code"));
        assertEquals("Test error", result.get("msg"));
    }

    @Test
    void testHandleDuplicateKeyException() {
        DuplicateKeyException ex = new DuplicateKeyException("Duplicate");
        R result = handler.handleDuplicateKeyException(ex);

        assertEquals("数据库中已存在该记录", result.get("msg"));
    }

    @Test
    void testHandleAuthorizationException() {
        AuthorizationException ex = new AuthorizationException("No permission");
        R result = handler.handleAuthorizationException(ex);

        assertEquals("没有权限，请联系管理员授权", result.get("msg"));
    }
}