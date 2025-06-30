package com.furan.modules.api;

import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public interface SysUserApiService {
    Long queryRoleById(Long userId);
}
