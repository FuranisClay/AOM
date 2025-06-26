package io.renren.modules.api;

import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Title: SysUserApiService
 * @Author itmei
 * @Package io.renren.modules.api
 * @Date 2025/6/26 22:38
 * @description: api
 */
@DubboService
public interface SysUserApiService {
    Long queryRoleById(Long userId);
}
