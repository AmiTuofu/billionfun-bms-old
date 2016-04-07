package com.billionfun.bms.product.mall.dao;

import java.util.List;

import com.billionfun.bms.product.mall.model.SysRole;
import com.billionfun.bms.product.mall.vo.SysRoleVO;

public interface SysRoleDao extends BaseDao<SysRole,String> {
	List<SysRole> getListByPage(SysRoleVO vo);
	
}
