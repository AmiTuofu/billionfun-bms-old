package com.billionfun.bms.product.mall.dao;

import com.billionfun.bms.product.mall.model.SysUser;
import com.billionfun.bms.product.mall.vo.SysUserVO;

public interface SysUserDao extends BaseDao<SysUser,String> {
	public SysUser loadUserByUsername(String username);
	
	public SysUser loadUser(String username,String email,String mobile);
	
	public boolean updatePWD(SysUserVO vo);
}
