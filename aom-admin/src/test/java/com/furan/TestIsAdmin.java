package com.furan;

import com.furan.modules.sys.service.impl.SysUserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestIsAdmin{
    @Autowired
    SysUserServiceImpl  sysUserService;

    @Test
    public void testIsAdmin() {
        Long l = sysUserService.queryRoleById(4L);
        System.out.println(l);
    }

}
