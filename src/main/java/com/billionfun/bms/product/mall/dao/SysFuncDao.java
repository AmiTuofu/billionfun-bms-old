package com.billionfun.bms.product.mall.dao;

import java.util.List;

import com.billionfun.bms.product.mall.model.SysFunc;
import com.billionfun.bms.product.mall.vo.SysFuncVO;

public interface SysFuncDao extends BaseDao<SysFunc,String> {
	public List<SysFunc> loadFunc(String parentId);
	
	public List<SysFunc> getListByPage(SysFuncVO funcVo);
	
	public List<SysFunc> getListByRoleId(String roleId);
	
}
