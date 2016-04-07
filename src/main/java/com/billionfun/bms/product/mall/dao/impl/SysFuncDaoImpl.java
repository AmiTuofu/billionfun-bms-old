package com.billionfun.bms.product.mall.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.billionfun.bms.product.mall.common.utils.Rule;
import com.billionfun.bms.product.mall.common.utils.StringUtil;
import com.billionfun.bms.product.mall.dao.SysFuncDao;
import com.billionfun.bms.product.mall.model.SysFunc;
import com.billionfun.bms.product.mall.model.SysRole;
import com.billionfun.bms.product.mall.vo.SysFuncVO;

@Repository
public class SysFuncDaoImpl extends BaseDaoImpl<SysFunc,String> implements SysFuncDao{

	
	public List<SysFunc> loadFunc(String parentId) {
		StringBuilder hql = new StringBuilder();
		hql.append(" from SysFunc ");
		hql.append(" where parentId = :parentId");
		Map<String, String> proMap = new HashMap<String, String>();
		proMap.put("parentId", parentId);
		
		List<SysFunc> list = super.findAll(hql.toString(), proMap);
		return list;
	}

	public List<SysFunc> getListByPage(SysFuncVO funcVo) {
		StringBuilder hql = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		hql.append(" from SysFunc f");
		hql.append(" where 1=1");
		if(funcVo.getSearch()){
			funcVo.getSearchHql(hql, funcVo.getFilters(), paramList);
		}
		if(!StringUtil.empty(funcVo.getSort())&&!StringUtil.empty(funcVo.getOrder())){
			hql.append(" order by f.").append(funcVo.getSort()).append(" ").append(funcVo.getOrder());
		}

//		List<Criterion> criterions = funcVo.generateSearchCriteriaFromFilters(funcVo.getFilters());
		List<SysFunc> list = super.getListByPage(funcVo, hql.toString(), paramList);
		return list;
	}

	public List<SysFunc> getListByRoleId(String roleId){
		StringBuilder hql = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		hql.append("select f from SysFunc f,SysRoleFunc rf");
		hql.append(" where f.id = rf.id.funcId and rf.id.roleId = ?");
		paramList.add(roleId);
		List<SysFunc> list = super.getList(hql.toString(),paramList);
		return list;
	}
}
