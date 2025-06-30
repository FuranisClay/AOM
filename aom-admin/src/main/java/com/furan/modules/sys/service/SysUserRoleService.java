package com.furan.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.furan.modules.sys.entity.SysUserRoleEntity;

import java.util.List;




public interface SysUserRoleService extends IService<SysUserRoleEntity> {

	void saveOrUpdate(Long userId, List<Long> roleIdList);

	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(Long[] roleIds);
}
