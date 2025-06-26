package io.renren.modules.sys.service.impl;

import io.renren.modules.api.SysUserApiService;
import io.renren.modules.sys.service.SysUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@DubboService
public class SysUserApiServiceImpl implements SysUserApiService {

    @Resource
    private SysUserService sysUserService;

    @Override
    public Long queryRoleById(Long userId) {
        return sysUserService.queryRoleById(userId);
    }
}
