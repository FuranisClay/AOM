package com.furan.modules.sys.service.impl;

import com.furan.modules.api.SysUserApiService;
import com.furan.modules.sys.service.SysUserService;
import org.apache.dubbo.config.annotation.DubboService;

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
